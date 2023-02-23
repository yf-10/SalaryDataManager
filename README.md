# SalaryDataManager

### Develop Environment
* Java : OpenJDK 17
* IDE : IntelliJ IDEA 2022.3.2 (Community Edition)
* Git : 2.39.1.windows.1

### HTTP Request Example
```
/salary

[GET]
curl -X GET "http://localhost:7777/salary/getByMonth?month=202302"
curl -X GET "http://localhost:7777/salary/getByMonthBetween?monthFrom=202102&monthTo=202312"
curl -X GET "http://localhost:7777/salary/getByMonthAfter?monthFrom=202102"
curl -X GET "http://localhost:7777/salary/getByMonthBefore?monthTo=202312"
curl -X GET -H "content-type: application/json" -d '{"month":"202303", "deduction":"true", "paymentItem":"xxx"}' "http://localhost:7777/salary/getById"

[POST]
curl -X POST -H "content-type: application/json" -d '{"month":"202303", "deduction":"true", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}' "http://localhost:7777/salary/post?createdBy=TEST"
curl -X POST -H "content-type: application/json" -d '{"month":"202303", "deduction":"true", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}' "http://localhost:7777/salary/post?createdBy=TEST&force=true"
curl -X POST -H "content-type: application/json" -d '{"month":"202303", "deduction":"false", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}' "http://localhost:7777/salary/post?createdBy=TEST&force=true"
curl -X POST -H "content-type: application/json" -d '[{"month":"202303", "deduction":"false", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}, {"month":"202304", "deduction":"false", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}]' "http://localhost:7777/salary/postAll?createdBy=TEST"
curl -X POST -H "content-type: application/json" -d '[{"month":"202303", "deduction":"false", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}, {"month":"202304", "deduction":"false", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}]' "http://localhost:7777/salary/postAll?createdBy=TEST&force=true"

[PUT]
curl -X PUT -H "content-type: application/json" -d '{"createdAt":"2023/02/23 00:00:00.001", "createdBy":"Fujino", "updatedAt":"2023/02/23 00:00:00.000", "updatedBy":"Fujino", "exclusiveFlag":0, "month":"202303", "deduction":"true", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}' "http://localhost:7777/salary/put?updatedBy=TEST2"
curl -X PUT -H "content-type: application/json" -d '{"createdAt":"2023/02/23 00:00:00.001", "createdBy":"Fujino", "updatedAt":"2023/02/23 00:00:00.000", "updatedBy":"Fujino", "exclusiveFlag":0, "month":"209903", "deduction":"true", "paymentItem":"TEST", "money":{"amount":10000, "currencyCode":"JPY"}}' "http://localhost:7777/salary/put?updatedBy=TEST2"

[DELETE]
curl -X DELETE -H "content-type: application/json" -d '{"month":"202303", "deduction":"true", "paymentItem":"xxx"}' "http://localhost:7777/salary/delete"
curl -X DELETE -H "content-type: application/json" -d '{"month":"202303", "deduction":"true", "paymentItem":"TEST"}' "http://localhost:7777/salary/delete"
```