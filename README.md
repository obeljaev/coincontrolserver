
### Описание Rest API
| Метод | Путь | Действие | Пример |
|-----------------|----------------------------------------------------------|---------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| GET | api/v1/users | Получение данных пользователей |  |
| GET | api/v1/users/{id} | Получение данных пользователя | api/v1/users/3 |
| POST | api/v1/users | Добавление нового пользователя | { 	 "name" : "Alex", 	 "password" : "123456" } |
| PUT | api/v1/users/{id} | Изменение данных пользователя | {  	  "name" : "AlexStruc",  	  "password" : "123456"  } |
| DELETE |  api/v1/users/{id} | Удаление пользователя и всего что с ним связано | api/v1/users/3 |
| GET | api/v1/wallets/{userId} | Получение данных о счетах пользователя | api/v1/wallet/10 |
| GET | api/v1/wallets/{userId}/{walletId} | Получение данных о счете пользователя | api/v1/wallet/3/1 |
| POST | api/v1/wallets/{userId} | Добавление нового счета | {     "name": "наличные",     "money": 2000 } |
| PUT | api/v1/wallets/{userId}/{walletId} | Изменение счета | {     "name": "наличные",     "money": 3000 } |
| DELETE | api/v1/wallets/{userId}/{walletId} | Удаление счета и всего что с ним связано | api/v1/wallet/3/1 |
| GET | api/v1/categories/{userId} | Получение данных о категориях пользователя | api/v1/category/3 |
| GET | api/v1/categories/{userId}/{categoryId} | Получение данных о категории/подкатегории пользователя | api/v1/category/3/1 |
| POST | api/v1/categories/{userId} | Добавление новой категории | { 	 "name" : "досуг" } |
| POST | api/v1/categories/{userId}/{categoryId} | Добавление подкатегории | { 	 "name" : "досуг" } |
| PUT | api/v1/categories/{userId}/{categoryId} | Изменение данных категории/подкатегории | { 	 "name" : "отдых" } |
| DELETE | api/v1/categories/{userId}/{categoryId} | Удаление категории | api/v1/category/3/1 |
| GET | api/v1/operations/{userId} | Получение данных об операциях пользователя |  |
| GET | api/v1/operations/{userId}/{operationId} | Получение данных об операции пользователя |  |
| POST | api/v1/operations/{userId}/{categoryId}/{fromWalletId}/{toWalletId} | Добавление новой операции перевода между счетами (! обязательно operationType=2)| { "money" : 1, "operatationType" : 2 } |
| POST | api/v1/operations/{userId}/{categoryId}/{walletId} | Добавление новой операции дохода (operationType=0) или расхода (operationType=1) | { "money" : 1, "operatationType" : 1 } |
| DELETE | api/v1/operations/{userId}/{operationId} | Удаление операции пользователя |  |
| GET | api/v1/verifications/{userId} | Получение данных о сверках реальных и ожидаемых денег пользователя |  |
| GET | api/v1/verifications/{userId}/{walletId} | Получение данных о сверках реальных и ожидаемых денег определенного счета пользователя |  |
| GET | api/v1/verifications/{userId}/{walletId}/{verificationId} | Получение данных о сверке реальных и ожидаемых денег |  |
| POST | api/v1/verifications/{userId}/{walletId} | Добавление новой сверки | { "actualMoney" : 50 } |
| GET | api/v1/verifications/{userId}/{walletId}/{verificationId} | Удаление сверки |  |
| GET |  | Расход за определенный период по выбранным категориям |  |
| GET |  | Доход за определенный период по выбранным категориям |  | 