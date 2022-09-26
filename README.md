# cft_test_task
# Back-End часть приложения для магазина, торгующего компьютерами и их комплектующими.
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
1. SwaggerConfig.java - настройка для Swagger, доступ к которому можно получить по следующей ссылке: http://localhost:8080/swagger-ui.html;
2. WebConfiguration.java - настройка Spring Boot Web.

### com.example.cft_test_task.CONTROLLER
1. TechnicsController.java - Rest controller, в котором описаны дествия для соответствующих запросов.  

### com.example.cft_test_task.MODEL
Здесь хранятся классы, моделирующие сущности для репозиториев, специфические параметры разного типа техники, а также модели REST запросов и ответов: 
#### com.example.cft_test_task.model.ENTITIES
В .technics находятся сущности для описания соответствующих им репозиториев. Все они осуществляют интерфейс TechEntityBase.java.

1. DesktopEntity.java -  настольные компьютеры, имеющие форм-фактор: десктопы, неттопы, моноблоки;
2. LaptopEntity.java - ноутбуки, подразделяемые по размеру: 13, 14, 15, 17 дюймовые;
3. DisplayEntity.java - мониторы, имеющие диагональ;
4. StorageEntity.java - жесткие диски, которые имеют объем.
#### com.example.cft_test_task.model.ENUMS.tech
#### com.example.cft_test_task.model.REST

