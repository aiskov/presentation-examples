%title: Inside in Spring
%author: Anton M. Iškov (iskov.anton@ya.ru)
%date: 2020-01-08

-> Inside in Spring <-
======================

-> Part 1: beans, beans, beans <-
---------------------------------

--------------------------------------------

-> Assumption & Goals <-
========================

-> I want to show how spring works inside, and describe how magic works. <-

-> *Disclaimer:* <-
^

-> *I'm not an expert!* <-

^
-> *Partially this presentation based on Evgeny Borisov (https://twitter.com/jekaborisov)*

--------------------------------------------

*Licence:* GNU General Public License v3.0
*Repository:* https://github.com/aiskov/presentation-examples

--------------------------------------------

-> Agenda: <-
------------

What we use in Spring:

^

* Spring MVC/Spring Web
* Spring Data
* Spring Security
* Spring Boot
* Spring Cloud

^

-> *I will not speak about that!* <-

^ 

-> *... and:* <-

* Transactions
* Scopes

--------------------------------------------

-> Agenda: <-
------------

But what I want to show
* Xml
^ 
* Xml
^
* BeanFactory & Application Context
^
* Lifecycle
^
* BeanPostProcessor & Proxies
^
* Application Event Listener
^
* I lied I will speak little beat about Singleton & Prototype 

-------------------------------------------------

~~~
|                      .-""-.          ( )-"```"-( )          .-""-.                  |
|                     / O O  \          /         \          /  O O \                 |
|                     |O .-.  \        /   0 _ 0   \        /  .-. O|    __________   |
|                     \ (   )  '.    _|     (_)     |     .'  (   ) /   /          \  |
|                      '.`-'     '-./ |             |`\.-'     '-'.'   < Good Luck  | |
|                        \         |  \   \     /   /  |         /      \__________/  |
|                         \        \   '.  '._.'  .'   /        /                     |
|                         \        '.   `'-----'`   .'        /                       |
|                          \   .'    '-._        .-'\   '.   /                        |
|                           |/`          `'''''')    )    `\|                         |
|                           /                  (    (      ,\                         |
|                          ;                    \    '-..-'/ ;                        |
|                          |                     '.       /  |                        |
|                          |                       `'---'`   |                        |
|                          ;                                 ;                        |
|                           \                               /                         |
|                            `.                           .'                          |
|                              '-._                   _.-'                            |
|                              __/`"  '  - - -  ' "`` \__                             |
|                             /`            /^\           `\                          |
|                             \(          .'   '.         )/                          |
|                              '.(__(__.-'       '.__)__).'                           |
~~~

--------------------------------------------

-> How Spring works <-
======================

~~~
                +------------+
 XML Files ===> | XML reader | 
                +------------+
                    |
                    | Defintions
                    V               I want beans
                +--------------+       +---------------------+
                | Bean Factory | <---- | Application Context |
                +--------------+       +---------------------+
                    |
                  +-----------------+
                +-----------------+ |
                | Post Processors |-+ 
                +-----------------+  
~~~


