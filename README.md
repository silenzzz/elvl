# Написать сервис для сохранения котировок и получения elvl по инструментам (бумагам) по REST API.

## Функциональные требования:
• Сервис получает котировки  
• Сервис хранит историю полученных котировок в БД  
• Сервис рассчитывает elvl (правила ниже)  
• Сервис предоставляет elvl по isin  
• Сервис предоставляет перечень всех elvls

Предусмотреть обработку большого потока котировок (100 в секунду) по нескольким бумагам, в том числе и
одновременную обработку нескольких котировок по одной бумаге.

## Сущности:
• Котировка (quote) состоит из следующих полей: isin, bid, ask  
• elvl (energy level) – лучшая цена по данному инструменту (isin).  
Правила расчёта elvl на основе котировки:
1. Если bid > elvl, то elvl = bid
2. Если ask < elvl, то elvl = ask
3. Если значение elvl для данной бумаги отсутствует, то elvl = bid
4. Если bid отсутствует, то elvl = ask  
   Реализовать простую валидацию входных данных для котировки:  
   • bid должен быть меньше ask  
   • isin – 12 символов
## Пример:
1. `ISIN: RU000A0JX0J2`  
   `Bid: 100.2`  
   `Ask: 101.9`  
   Котировка новая, поэтому elvl - > 100.2
2. Пришла новая котировка, уже существует elvl  
   `ISIN: RU000A0JX0J2`  
   `Bid: 100.5`  
   `Ask: 101.9`  
   Так как Bid > elvl , то новый elvl = 100.5 (bid)
## Стек технологий:
• Kotlin/Java8  
• Maven/Gradle  
• Spring boot  
• Spring MVC, Spring Test  
С помощью тестов продемонстрируйте, что функционал работает так, как ожидается.