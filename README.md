# Библиотека

## О проекте:
Пример учебного приложения для управления библиотекой.
Это MVP-версия, которая позволяет осуществлять следующие действия:
 - Добавление, удаление и редактирование книг (функционал доступен только для администратора)
 - Поиск книг по названию, автору или жанру (функционал доступен всем пользователям)
 - Регистрация пользователей (доступна только для пользователей, не вошедших в систему)
 - Авторизация зарегистрированных пользователей
 
Помимо этого происходит логирование действий пользователей (логи записываются в файл в папку logs),
а также используется Swagger для документирования приложения.

Все операции поиска доступны по частичному совпадению.

### Технологии

__Java17__
__Maven 3.8__
__Hibernate__
__PostgreSQL__
__Spring boot__
__Spring security__
__Liquibase__
__Junit__
__Swagger__


![](https://img.shields.io/badge/Spring%20Boot-g)
![](https://img.shields.io/badge/Liquibase-red)
![](https://img.shields.io/badge/PostgreSQL-blue)
![](https://img.shields.io/badge/Spring%20Security-g)
![](https://img.shields.io/badge/Maven-red)
![](https://img.shields.io/badge/Hibernate-blue)


#### Запуск проекта:
Перед запуском приложения необходимо создать базу данных library в PostgreSQL 14
с помощью команды ```create database library```
Затем необходимо открыть и запустить класс src/main/java/org/example/library/LibraryApp.java.


#### Взаимодействие с приложением:
Рассмотрим использование приложения с использованием Swagger.
После запуска приложения заходим в браузер и переходим по ссылке: http://localhost:8080/swagger-ui/index.html#/
Откроется страница:
![img.png](images/img.png)

Поскольку вход в систему не был осуществлен, если попробовать отправить любой запрос, кроме регистрации, 
приложение сообщит вам о том, что вы не вошли в систему. Ниже пошаговый пример с попыткой обновить книгу:

![img1.png](images/img1.png)
![img2.png](images/img2.png)
![img3.png](images/img3.png)
![img4.png](images/img4.png)

Такая же ситуация будет и со всеми другими запросами.
Чтобы начать взаимодействовать с системой необходимо либо войти в систему.
Зарегистрируем нового пользователя и зайдем в систему.

Регистрация (Authentication Controller -> post register):
вводим имя, логин и пароль:
![img5.png](images/img5.png)
получаем ответ об успешной регистрации с email, который зарегистрирован в системе:
![img6.png](images/img6.png)


Аутентификация и авторизация:
процес аутентификации происходит путем ввода email и пароля.
При успешном прохождении аутентификации генерируется авторизационный токен, 
который предоставляет права на использование функционала приложения.

входим в систему и получаем токен (Authentication Controller -> post login):
![img7.png](images/img7.png)
наш токен:
![img8.png](images/img8.png)
Копируем токен и переходим в раздел Authorize в правой верхней части экрана:
![img9.png](images/img9.png)
после нажатия откроется форма, куда нужно вставить полученный токен (без кавычек и без других слов, только токен):
![img10.png](images/img10.png)

Поскольку по умолчанию пользователь регистрируется без прав администратора, после входа в систему 
под только что зарегистрированным пользователем будут доступны следующие функции:
- Поиск книг по названию (Book Controller -> get allByTitle)
![img11.png](images/img11.png)
- Поиск книг по жанру (Book Controller -> get allByGenre)
![img12.png](images/img12.png)
- Поиск книг по имени автора (Book Controller -> get allByAuthor)
![img13.png](images/img13.png)

Если попробовать выполнить дейстиве, которое доступно только администратору, приложение не даст это сделать.
Например, попробуем удалить книгу (Book Controller -> delete {id}):
![img14.png](images/img14.png)

Также если пользователь уже вошел в систему, он не сможет зарегистрировать нового пользователя:
![img15.png](images/img15.png)
![img16.png](images/img16.png)

Теперь рассмотрим функционал, доступный для администратора.
Администратору доступно все то же, что и обычному пользователю и также он может добавлять, удалять и редактировать книги.

Выйдем из профиля текущего пользователя и перезайдем в систему от имени администратора.
Выход:
![img17.png](images/img17.png)
![img18.png](images/img18.png)

Вход с правами администратора (Authentication Controller -> post login):
вводим:
```
{
"email": "admin@library.com",
"password": "password"
}
```
![img19.png](images/img19.png)
![img20.png](images/img20.png)

Копируем токен и вставляем в раздел Authorize, как это было описано выше.
![img21.png](images/img21.png)

Теперь можно использовать функции для администратора:
Сделаем запрос на поиск книг по автору (Book Controller -> get allByAuthor):
![img22.png](images/img22.png)

Сохраним новую книгу для данного автора (Book Controller -> post /)
и сделаем повторный запрос (Book Controller -> get allByAuthor):
сохранение:
![img23.png](images/img23.png)
![img24.png](images/img24.png)

делаем повторный поиск по имени автора, чтобы убедиться, что сохранение произошло (Book Controller -> get allByAuthor):
![img25.png](images/img25.png)

Отредактируем название книги (Book Controller -> put /{id}):
![img26.png](images/img26.png)
![img27.png](images/img27.png)

делаем повторный поиск по имени автора, чтобы убедиться, что обновление произошло (Book Controller -> get allByAuthor):
![img28.png](images/img28.png)

проверим, какие книги нам доступны по части названия (Book Controller -> get allByTitle):
![img29.png](images/img29.png)

проверим, какие книги нам доступны по жанру (Book Controller -> get allByGenre):
![img30.png](images/img30.png)

удалим книгу (Book Controller -> delete {id}):
![img31.png](images/img31.png)

проверим, какие книги нам доступны по жанру после удаления (Book Controller -> get allByGenre):
![img32.png](images/img32.png)

#### Контакты
Телеграм:
[Связаться со мной](https://t.me/M_Ponomarev_java_dev)