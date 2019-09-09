# Wallet MicroService

## Concurrency Handling
for handling concurrency, we can use different way
### 1-Optimistic 
it's a good way to handle concurrency most of the time it uses a field for handling concurrency timestamp or increment number but in very high request system some time you get in optimistic trap and you should try it again.
### 2-Pessimistic
it's another way to handle concurrency it's set lock in the database and your application and some time maybe you get in the pessimistic trap( set lock in reading time ).
### 3-Third party(Redlock)
a system like redlock it's a great way because you move your lock to another system like redis and it's very faster than the database.
### 4- Database Internal Ability 
atomic database update, as you know insert, delete, update is atomic in the relational database and it doesn't any side effect and i use it for my applications most of the time.

`[notice]` in this situation I use `Database Internal Ability` for our wallet balance because it can satisfy our concurrency problem very well

## Api Address

### credit 
  method `post` url `localhost:8009/wallet/credit`
 body 
 ```
 {
	"playerId":10,
	"transactionId":"t1",
	"amount":10.01
}
 ```
 
 ### debit 
  method `post` url `localhost:8009/wallet/debit`
 body 
 ```
 {
	"playerId":10,
	"transactionId":"t2",
	"amount":10.01
}
 ```
 
  ### get player balance 
  method `get` url `localhost:8009/wallet/balance/{playerId}`
 
 ### get all player transaction
 method `get` url `localhost:8009/wallet/playerTransaction/{playerId}`

### Todos

 - Write MORE Tests (because I don't have enough time)
 - Add swagger