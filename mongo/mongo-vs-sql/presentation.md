# Mongo vs/to SQL 

## Simple select

```sql
SELECT * FROM people
```

```javascript
db.people.find()
```

### Projection

```sql
SELECT user_id, status
FROM people
```

```javascript
db.people.find(
    { },
    { user_id: 1, status: 1, _id: 0 }
)
```

### Conditions:

```sql
SELECT *
FROM people
WHERE status = "A"
AND age = 50
```

```sql
SELECT *
FROM people
WHERE status = "A"
OR age = 50
```

```sql
SELECT *
FROM people
WHERE user_id like "bc%"
```

```javascript
db.people.find(
    { status: "A", age: 50 }
)
```

```javascript
db.people.find({ 
    $or: [ 
        { status: "A" } , 
        { age: 50 } 
    ] 
})
```

```javascript
db.people.find( { user_id: /^bc/ } )
```

### Order 

```sql
SELECT *
FROM people
ORDER BY user_id ASC, create_date DESC
```

```javascript
db.people.find().sort( { user_id: 1, create_date: -1 } )
```

### Limit

```sql
SELECT *
FROM people
LIMIT 100, 10
```

```javascript
db.people.find()
    .limit(10)
    .skip(100)
```

### Explain

```sql
EXPLAIN SELECT *
FROM people
WHERE status = "A"
```

```javascript
db.people.find( { status: "A" } ).explain()
```

### Count

```sql
SELECT count(*)
FROM people
```

```javascript
db.people.count()
```

## Aggregations

### Distinct one field

```sql
SELECT DISTINCT(status)
FROM people
```

```javascript
db.people.aggregate([ 
    { $group: { _id: "$status" } }
])
```

```sql
SELECT DISTINCT(status)
FROM people
WHERE status = "A"
```

```javascript
db.people.aggregate([
    { $match: { author: "dave" } }, 
    { $group: { _id: "$status" } }
])
```

## Examples 

```sql
SELECT invoice_month, invoice_year, sum(id), sum(gross_total)
FROM invoice
WHERE status in ("PUBLISHED", "PAID")
GROUP BY month(invoice_date) as invoice_month, year(invoice_date) as invoice_year
```

```javascript
db.getCollection('invoice').aggregate([
    {
        "$match": {
            "status": {"$in": ["PUBLISHED", "PAID"]},
        }
    }, 
    
    {
        "$addFields": {
            "grossTotal": {"$toDecimal": "$grossTotal"},
            "month": {"$dateToString": {"format": "%Y-%m", "date": "$createdTime"}}
        }
    }, 
    
    {
        "$group": {
            "_id": "$month",
            "quantity": {"$sum": 1},
            "grossTotal": {"$sum": "$grossTotal"},
        }
    }, 
    
    {
        "$sort": {"_id": 1}
    }
])
```

```sql
SELECT invoice_row.code, invoice_row.name, sum(quantity), sum(grossTotal)
FROM invoice_row
    JOIN invoice ON invoice_row.invoice_id = invoice.id
WHERE invoice.status in ("PUBLISHED", "PAID")
GROUP BY invoice_row.code, invoice_row.name
LIMIT 5;
```

```javascript
db.getCollection('invoice').aggregate([
    {
        "$match": {
            "status": {"$in": ["PUBLISHED", "PAID"]}
        }
    },
    // Get internal collection as separate objects
    {
        "$unwind": "$items"
    },
    {
        "$replaceRoot": {"newRoot": "$items"}
    },
    {
        "$addFields": {
            "_group_code": {
                "$cond": {
                    "if": {
                        "$ne": ["$code", null]
                    },
                    "then": "$code",
                    "else": "$article"
                }
            },
            "quantity": {"$toDecimal": "$quantity"},
            "grossTotal": {"$toDecimal": "$discounted.gross.total"}
        }
    },

    {
        "$group": {
            "_id": "$_group_code",
            "code": {"$first": "$code"},
            "name": {"$first": "$article"},
            "quantity": {"$sum": "$quantity"},
            "grossTotal": {"$sum": "$grossTotal"}
        }
    },

    // Sort results
    {
        "$sort": {
            "grossTotal": -1
        }
    },

    // Apply limit
    {
        "$limit": 5
    }
])
```

```javascript
db.getCollection('invoice').aggregate([
    {
        "$match": {
            "status": {"$in": ["PUBLISHED", "PAID"]},
        }
    }, 
    {
        "$addFields": {
            "grossTotal": {"$toDecimal": "$grossTotal"}
        }
    }, 
    {
        "$group": {
            "_id": "$client",
            "quantity": {"$sum": 1},
            "grossTotal": {"$sum": "$grossTotal"}
        }
    }, 
    {"$sort": {"grossTotal": -1}}, 
    {"$limit": 5}
])
```

Invoices could be in status PAID, PUBLISHED, DRAFT, CANCELED. DRAFT and CANCELED shouldn't appear in statistics.

```javascript
db.getCollection('invoice').aggregate([
    {
        "$match": {
            "status": "PUBLISHED"
        }
    }, 
    {
        "$addFields": {
            "grossTotal": {"$toDecimal": "$grossTotal"}
        }
    }, 
    {
        "$addFields": {
            "lateGrossTotal": {
                "$cond": {
                    "if": {"$lt": ["$term", {"$date": "2022-04-12T00:00:00Z"}]},
                    "then": "$grossTotal",
                    "else": 0
                }
            },
            "lateQuantity": {
                "$cond": {
                    "if": {"$lt": ["$term", {"$date": "2022-04-12T00:00:00Z"}]},
                    "then": 1,
                    "else": 0
                }
            }
        }
    }, 
    {
        "$group": {
            "_id": "$all",
            "quantity": {"$sum": 1},
            "grossTotal": {"$sum": "$grossTotal"},
            "lateQuantity": {"$sum": "$lateQuantity"},
            "lateGrossTotal": {"$sum": "$lateGrossTotal"},
        }
    }])
```

```sql
SELECT count(id), sum(grossTotal), sum(lateQuantity), sum(lateGrossTotal)
FROM (
    SELECT 
           id, 
           grossTotal,
           IF(date < ?, 1, 0) as lateQuantity
           IF(date < ?, grossTotal, 0) as lateGrossTotal
    FROM invoice
)
```

## References
1. https://www.mongodb.com/docs/manual/reference/sql-comparison/