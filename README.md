Что это?
--------
Приложение работает с двумя сайтами: <br>
https://finance.yahoo.com  который содержит информацию о компаниях с точки зрения биржевой торговли (котировки, новости бизнеса и так далее). <br>
И https://www.indeed.com - сайт для поиска работы или сотрудников. <br><br>
Информация о многих компаниях содержится на обоих сайтах, но отличается в зависимости от направления деятельности сайта. <br>
Тем не менее и там и там в профиле компаний есть поле " Industry" которое часто заполнено по разному. <br><br>
Данное приложение работает в два этапа:<br>
На первом этапе оно ищет названия и тикеры таких компаний которые представлены на обоих сайтах.<br>
На втором этапе программа идёт по полученному списку и запрашивает значения полей "Industry" для каждой компании, объединяет их в одну строку и выводит в консоль в виде:<br>        
    
    1- Alphabet:
    Internet Content & Information Information Technology 

Технологии:
----------
В качестве инструмента сборки использован Maven<br>
Для парсинга html страниц Jsoup.<br>
Поиск тикеров компаний осуществляется с помощью ReqEx

Предложения по улучшению:
-------------------------
 Программа может запрашивать у пользователя название компании, после чего выводить информацию из полей "Industry" двух сайтов в приведённом выше формате. <br><br>
Основная проблема с которой предстоит столкнуться при реализации данного предложения это поиск информации о ticker-символе компании, необходимом для работы с сайтом https://finance.yahoo.com.<br><br>
Использование для этой цели поисковых систем (Google, Яндекс) невозможно т.к. они отказываются работать с автоматическими запросами, поэтому необходим отдельный реестр ticker-символов. 

Контакты:
--------
Меня зовут **Игорь Мешалкин**,<br> если у вас возникли вопросы или предложения со мной можно связаться следующим образом:<br><br>
Телефон **+7 (962) 500-03-73** (WhatsApp)<br>
Почта: 770190@bk.ru
