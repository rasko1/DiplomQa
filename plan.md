# План автоматизации тестирования сервиса "Путешествие дня"

### Для автоматизации тестов необходимо автоматизировать не только сам ввод валидных/невалидных данных, но и действия для перехода на страницу сервиса и выбора способа оплаты.

### Входные данные:
> <details>
> <summary>Развернуть список:</summary> 
>Валидные карты для тестирования:
>
>- 4444 4444 4444 4441, status APPROVED
>- 4444 4444 4444 4442, status DECLINED
>
>Валидные данные для карт:
>- Номер карты из 16 цифр.
>- Месяц в формате от 01 до 12 (не раньше текущего месяца в текущем году).
>- Год в формате двух последних цифр полного номера года. (например, 23, 24 и тд)
>- Владелец в формате двух слов (Имя и Фамилия) буквами латинского алфавита
>- CVC в формате 3х цифр.
>
>Валидные данные срока действия карты устанавливаются не ранее текущей даты и не старше 5 лет от текущей даты.
> </details>    
---

### Перечень автоматизируемых сценариев
#### Позитивные сценарии:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Нажимаем кнопку "Купить" 
>- вводим валидные данные карты 4444 4444 4444 4441. 
>- Нажимаем кнопку "Продолжить". 
>- Ожидаемый результат "Успешно". 
>- В БД в payment_entity появилась запись со статусом APPROVED.
>
> 2) Нажимаем кнопку "Купить"
>- Вводим валидные данные карты 4444 4444 4444 4442. 
>- Нажимаем кнопку "Продолжить". 
>- Ожидаемый результат "Ошибка". 
>- В БД в payment_entity появилась запись со статусом DECLINED.
>
> 3) Нажимаем кнопку "Купить в кредит"
>- Вводим валидные данные карты 4444 4444 4444 4441. 
>- Нажимаем кнопку "Продолжить". 
>- Ожидаемый результат "Успешно". 
>- В БД в credit_request_entity появилась запись со статусом APPROVED.
>
> 4) Нажимаем кнопку "Купить в кредит" 
>- Вводим валидные данные карты 4444 4444 4444 4442. 
>- Нажимаем кнопку "Продолжить". 
>- Ожидаемый результат "Ошибка". 
>- В БД в credit_request_entity появилась запись со статусом DECLINED.
> </details>
---

#### Негативные сценарии:
> <details>
> <summary>Развернуть список:</summary>
>
>#### 1) Невалидные данные номера карты:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Оставляем пустое поле.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Неверный формат".
>
> 2) Заполняем поле невалидным номером карты, например: 4455 5666 6555 5555.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат сообщение "Ошибка".
>
> 3) Заполняем поле карты не полностью (менее 16 знаков).
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат сообщение "Неверный формат".
>
> 4) Заполняем поле карты более 16 знаков.
>- Остальные поля заполняем валидными данными.
>- Ожидаемый результат: символы больше не вводятся в поле ввода.
>
> 5) Заполняем поле карты буквами (рус\лат).
>- Ожидаемый результат: буквы не вводятся.
>
> 6) Заполняем поле карты спецсимволами.
>- Ожидаемый результат: символы не вводятся.
> </details>
>
>#### 2) Невалидные данные в поле месяц:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Оставляем пустое поле.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Неверный формат".
>
> 2) Заполняем поле цифрами до 10, не ставя впереди 0.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Неверный формат".
>
> 3) Заполняем поле ввода более 2 символов.
>- Ожидаемый результат: символы больше 2 символов не вводятся в поле ввода.
>
> 4) Заполняем поле карты буквами и спецсимволами.
>- Ожидаемый результат: буквы и символы не вводятся.
>
> 5) Заполняем поле ввода цифрами 13 и более.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: "Неверно указан срок действия карты"
> </details>
>
>#### 3) Невалидные данные в поле год:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Оставляем пустое поле.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Неверный формат".
> 2) Заполняем поле годом меньше текущего.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Истёк срок действия карты".
> 3) Заполняем поле ввода более 2 символов.
>- Ожидаемый результат: символы больше 2 символов не вводятся в поле ввода.
> 4) Заполняем поле карты буквами и спецсимволами.
>- Ожидаемый результат: буквы и символы не вводятся.
> 5) Заполняем поле годом, чтобы дата(месяц, год) превышала срок действия карты.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: "Неверно указан срок действия карты"
> </details>
>
>#### 4) Невалидные данные в поле Владелец:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Оставляем пустое поле.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Поле обязательно для заполнения".
> 2) Заполняем поле именем Владельца на русском языке.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке.
> 3) Заполняем поле одним словом.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке.
> 4) Заполняем поле тремя и более словами.
>- Ожидаемый результат: сообщение об ошибке
> 5) Заполняем поле цифрами.
> - Ожидаемый результат: цифры не вводятся.
> 6) Заполняем поле спецсимволами.
>- Ожидаемый результат: спецсимволы не вводятся
> </details>
>
>#### 5) Невалидные данные в поле CVC/CVV:
> <details>
> <summary>Развернуть список:</summary>
>
> 1) Оставляем пустое поле.
>- Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Поле обязательно для заполнения".
> 2) Вводим значения менее трех цифр.
> - Остальные поля заполняем валидными данными.
>- Нажимаем кнопку "Продолжить".
>- Ожидаемый результат: сообщение об ошибке "Неверный формат"
> 3) Вводим значения более трех цифр.
>- Ожидаемый результат: значения более 3х символов не вводятся
> 5) Вводим значения буквами.
>- Ожидаемый результат: символы не вводятся
> 6) Вводим значения спецсимволами.
>- Ожидаемый результат: символы не вводятся.
> </details>
> </details>

## Перечень используемых инструментов

### Docker
Среда для контейнеризации софта, позволяющая легко создавать и передавать отдельные контейнеры с софтом, которые работают везде одинаково.

### Docker-compose
Инструмент для создания и запуска многоконтейнерных Docker приложений. В Compose, используется специальный файл для конфигурирования тестируемых сервисов приложения. Compose превосходен для разработки, тестирования и настройки среды, а также непрерывной интеграции.

### IntelliJ IDEA
Несмотря на то, что IntelliJ IDEA — в первую очередь IDE для Java, она понимает и предоставляет интеллектуальную помощь при написании кода на SQL, JPQL, HTML, JavaScript и многих других языках и позволяет редактировать код, написанный не на Java, внутри строковых литералов Java-кода.

### Java 11
Java — едва ли не единственный язык, на свежих версиях которого можно запускать код, написанный 25 лет назад. Разработчики языка очень серьезно относятся к обратной совместимости, поэтому многие организации выбирают Java в качестве основной технологии, зная, что старый код будет запускаться на JVM еще долгие годы.
Сам язык и решения, которые он предлагает, хорошо документированы и поддерживаются вендорами и некоммерческими организациями, а также отдельными пользователями.

### Gradle
Альтернатива Maven, но:
- скрипты сборки на Gradle на много короче и удобнее в написании
- Gradle быстрее maven
- Gradle был создан для расширяемых многопроектных сборок, и поддерживает инкрементальные сборки, определяя, какие компоненты дерева сборки не изменились и какие задачи, зависимые от этих частей, не требуют перезапуска.
- в Gradle собраны лучшие качества от Maven и Ant
- Gradle легко масштабируется
### JUnit 5
- На сегодняшний день это самая популярная платформа автоматизированного тестирования в мире Java. Как JUnit, так и TestNG поставляются вместе с IntelliJ IDEA: предполагается, что для любого нового Java-проекта необходим фреймворк тестирования. Вполне вероятно, что современные тестовые фреймворки для самых разных языков основаны на идеях, впервые реализованных в JUnit. Культура автоматизированного тестирования, принятая в Java-сообществе, во многом обязана именно этой библиотеке.
- В виду популярности данной платформы, много поддерживаемых модулей и документации.
### Selenide
- это обёртка вокруг Selenium WebDriver, позволяющая быстро и просто его использовать при написании тестов, сосредоточившись на логике, а не суете с браузером.
### Lombok
- библиотека, с помощью которой мы можем сократить количество шаблонного кода, который нужно писать на Java.
### Allure
Популярный инструмент построения отчётов авто-тестов, упрощающий их анализ. Это гибкий и легкий инструмент, который позволяет получить не только краткую информацию о ходе выполнения тестов, но и предоставляет всем участникам производственного процесса максимум полезной информации из повседневного выполнения автоматизированных тестов.  
Разработчикам и тестировщикам использование отчетов Allure позволяет сократить жизненный цикл дефекта: падения тестов могут быть разделены на дефекты продукта и дефекты самого теста, что сокращает затраты времени на анализ дефекта и его устранение. Также к отчету могут быть прикреплены логи, обозначены тестовые шаги, добавлены вложения с разнообразным контентом, получена информация о таймингах и времени выполнения тестов. Кроме того, Allure-отчеты поддерживают взаимодействие с системами непрерывной интеграции и баг-трекинговыми системами, что позволяет всегда держать под рукой нужную информацию о прохождении тестов и дефектах. Инструмент имеет модульную структуру, позволяющую легко интегрировать его с уже используемыми инструментами автоматизации тестирования.


## Перечень необходимых разрешений/данных/доступов
- В случае с проверкой поля "номер карты" необходимо от заказчика получить информацию, каких форматов обслуживаются карты в данном сервисе (13,15,16,18 и 19 цифр в номере карты)

## Перечень и описание возможных рисков при автоматизации
- Изменение элементов в коде может повлиять на работу авто-тестов и в конечном счете их придется править/чинить.
- Увеличение времени на тестирование и его стоимость. (Автоматизация тестов имеется смысл, только если проверка модуля будет необходима с какой-то периодичностью.)

## Перечень необходимых специалистов для автоматизации
Для написания авто-тестов на данный модуль потребуется 1 специалист QA

## Интервальная оценка с учётом рисков (в часах)
Для выполнения автоматизации тестирования, с учетом рисков, потребуется от 48 до 96 часов, в зависимости от квалификации специалиста QA и нагрузки по другим проектам.