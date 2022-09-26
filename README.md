# cft_test_task
# Back-End часть приложения для магазина, торгующего компьютерами и их комплектующими.
### [скачивание и установка](https://github.com/egorkuzn/cft_test_task/tree/master/install)
Для каждой категории были созданы репозитории в com.example.cft_test_task.repos.technics, которые заданы следущими сущностями из com.example.cft_test_task.model.entities.technics.
Для того, чтобы ограничить сущности типов техники, был создан интерфейс com.example.cft_test_task.model.entities.technics.TechEntityBase. В нем определены бызовые get и set для поля
общих данных товара, без учета особенностей его типа. Это поле получено операцией соединения реляционной алгебры join связью "один к одному". Это позволяет нам использовать удобную 
работу с парой таблиц: таблицей, отвечающей за хранение товара одного вида, и таблицей всего товара. Во-первых, это позволяет сэкономить память, т.к. нам не нужно в таблице конкретного
вида техники добавлять не относящиеся к нему столбцы, а также в некоторых местах вместо float использовать enum(как в случае с диагональю у монитора и дисплея ноутбука), во-вторых делает
код стройным и логичным. 

Это хорошо видно в сервисах com.example.cft_test_task.service, а так же com.example.cft_test_task.repos.TechTypeRepo. Была попытка создать generic-класс вида:
````
@Repository
public interface TechTypeRepo<TypeEntity extends TechEntityBase> extends JpaRepository<TypeEntity, Long> {
    TypeEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}

````
Но это все работало не так, как изначально задумывалось: при вызове метода save() в репозитории не сохранялась сущность вместе со своими уникальными параметрами (диагональ, форм-факторб
размер памяти). Тем самым эксперентальным путем выяснилось, что под каждую сущность нужен уникальный _@Repository_. Но generic-класс com.example.cft_test_task.repos.TechTypeRepo всё же был нужен для generic-класса com.example.cft_test_task.service.technics.ServiceByTechType.
Поэтому и был создан такой своеобразный сервис. На этом небольшое введение можно закончить, перейдём к самой структуре:

### com.example.cft_test_task.CONFIG
1. **SwaggerConfig.java** - настройка для Swagger, доступ к которому можно получить по следующей ссылке: http://localhost:8080/swagger-ui.html;
2. **WebConfiguration.java** - настройка Spring Boot Web.

### com.example.cft_test_task.CONTROLLER
1. **TechnicsController.java** - Rest controller, в котором описаны дествия для соответствующих запросов.  

### com.example.cft_test_task.MODEL
Здесь хранятся классы, моделирующие сущности для репозиториев, специфические параметры разного типа техники, а также модели REST запросов и ответов: 
#### .ENTITIES
В **.technics** находятся сущности для описания соответствующих им репозиториев. Все они осуществляют интерфейс TechEntityBase.java.

1. **DesktopEntity.java** -  настольные компьютеры, имеющие форм-фактор: десктопы, неттопы, моноблоки;
2. **LaptopEntity.java** - ноутбуки, подразделяемые по размеру: 13, 14, 15, 17 дюймовые;
3. **DisplayEntity.java** - мониторы, имеющие диагональ;
4. **StorageEntity.java** - жесткие диски, которые имеют объем.
#### .ENUMS.tech
1. В **.details** описываются специфические параметры для настольного компьютера и ноутбука (формфактор и диагональ дисплея соотв.);
2. **TechnicFields.java** - всевозможные изменяемые параметры техники;
3. **TechnicTypes.java** - типы техники.
#### .REST
1. **request.TechnicsRequest.java** - запрос-форма на добавление нового товара;
2. **response.TechnicsResponse.java** - ответ-форма на получение информации о товаре.
### com.example.cft_test_task.REPOS
Репозитории (базы данных), сформированые на базе com.example.cft_test_task.model.ENTITIES
1. В **.technics** предствлены интерфейсы-наследники JpaRepository<Type, Long>, где type - класс-наследник от
   TechEntityBase.java из entity.technics. Другими словами: репозитории по типу техники;
2. **TechnicsRepo.java** - репозиторий всей техники, в котором указаны:
   * номер серии;
   * производитель;
   * цена;
   * количество единиц продукции на складе;
3. **TechTypeRepo.java** - generic-класс-сервис для удобной работы с групой репозиториев .technics.
### com.example.cft_test_task.SERVICE
**TechnicsService** - сервис, реализующий поведение com.example.cft_test_task.controller.TechnicsController
#### .TECHNICS
1. AnyTechService.java - абстрактный класс, который в первоначальном виде был базой сервисов, соответствующих каждому
типу техники. В процессе он взял на себя сам исполнение функций для конкретных TypeEntity из generic-класса ServiceByTechType.java;
2. ServiceByTechType.java - generic-класс для исполнения команд REST-запросов. Поэтому он определяет абстрактные методы AnyTechService.java:
* .add();
* .edit();
* .delete();
* .getById();
* .getAll();
