
### Описание Rest API
| Метод | Путь | Действие | Пример |
|-----------------|----------------------------------------------------------|---------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| GET | api/v1/user | Получение данных всех пользователей |  |
| POST | api/v1/user | Добавление нового пользователя | { 	 "userId" : 0, 	 "userName" : "Alex", 	 "userPassword" : "123456" } |
| PUT | api/v1/user | Изменение данных пользователя | {  	  "userId" : 2,  	  "userName" : "AlexStruc",  	  "userPassword" : "123456"  } |
| DELETE |  api/v1/user/{id} | Удаление пользователя и всего что с ним связано по ID | api/v1/user/3 |
| GET | api/v1/wallet/{userId} | Получение данных о счетах по ID пользователя | api/v1/wallet/10 |
| POST | api/v1/wallet | Добавление нового счета | {     "walletId": 0,     "userId": 10,     "walletName": "наличные",     "walletMoney": 2000 } |
| DELETE | api/v1/wallet/{userId}/{walletId} | Удаление счета по ID пользователя и по ID счета и всего что с ним связано | api/v1/wallet/8/6 |
| GET | api/v1/category/{userId} | Получение данных о категориях расходов/доходов | api/v1/category/10 |
| POST | api/v1/category | Добавление новой категории | { 	 "userId" : 10, 	 "categoryId" : 0, 	 "categoryName" : "досуг" } |
| PUT | api/v1/category | Изменение данных категории | { 	 "userId" : 2, 	 "categoryId" : 1, 	 "categoryName" : "отдых" } |
| DELETE | api/v1/category/{userId}/{categoryId} | Удаление категории | api/v1/category/10/1 |
| GET | api/v1/subCategory/{userId}/{categoryId} | Получение данных подкатегории |  |
| POST | api/v1/subCategory | Добавление новой подкатегории | { 	 "userId" : 2, 	 "categoryId" : 2, 	 "subCategoryId" : 0, 	 "subCategoryName" : "кино" } |
| PUT | api/v1/subCategory | Изменение подкатегории | {  	  "userId" : 2,  	  "categoryId" : 2,  	  "subCategoryId" : 10,  	  "subCategoryName" : "театр"  } |
| DELETE | api/v1/subCategory/{userId}/{categoryId}/{subCategoryId} | Удаление подкатегории | api/v1/category/10/1/10 |
| GET/POST |  | Получение и добавление данных об операции |  |
| GET/POST/DELETE |  | Данные о верификации денег на счетах |  |
| GET |  | Расход за определенный период по выбранным категориям |  |
| GET |  | Доход за определенный период по выбранным категориям |  |