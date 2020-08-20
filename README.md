# Restaurant-Ordering-System
NUIST SQL course design

[toc]

## table 表

```sql
CREATE TABLE `table` (                -- 餐桌表
  `TNo`   int NOT NULL,               -- 桌编号
  `TPass` char(6) DEFAULT '000000',   -- 验证码
  `TAvl`  tinyint DEFAULT '1',        -- 可用性
  PRIMARY KEY (`TNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

## dish 表

```sql
use order_recipe;
CREATE TABLE `dish` (               -- 菜品表
  `DId`     int NOT NULL,           -- 编号
  `DName`   varchar(20),            -- 名称
  `DDetail` varchar(50),            -- 详情
  PRIMARY KEY (`DId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

## food 表

```sql
use order_recipe;
CREATE TABLE `food` (               -- 食材表
  `FId`     int NOT NULL,           -- 编号
  `FName`   varchar(20),            -- 名称
  `FDetail` varchar(50),            -- 详情
  `FRes`    int,                    -- 剩余量
  `FType`   varchar(20),            -- 种类（肉、蔬菜等）
  PRIMARY KEY (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

## dish_food 表

```sql
use order_recipe;
CREATE TABLE `dish_food` (                        -- 菜品-食材
  `DId` int NOT NULL,                             -- 菜品编号
  `FId` int NOT NULL,                             -- 食材编号
  KEY `DId_idx` (`DId`),
  KEY `FId_idx` (`FId`),
  PRIMARY KEY (`DId`,`FId`),
  FOREIGN KEY (`DId`) REFERENCES `dish` (`DId`),
  FOREIGN KEY (`FId`) REFERENCES `food` (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

## table_dish 表

```sql
CREATE TABLE `table_dish` (         -- 桌订单表
  `TNo` int NOT NULL,               -- 桌编号
  `DId` int NOT NULL,               -- 菜品编号
  `TDNum` int DEFAULT '1',          -- 订购数量
  PRIMARY KEY (`TNo`,`DId`),
  KEY `table_dish_ibfk_1_idx` (`DId`),
  CONSTRAINT `table_dish_ibfk_1` FOREIGN KEY (`TNo`) REFERENCES `table` (`TNo`),
  CONSTRAINT `table_dish_ibfk_2` FOREIGN KEY (`DId`) REFERENCES `dish` (`DId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

## 增加桌订单

若**已存在**该订单则**数量+1**

```sql
insert into `table_dish` (TNo, DId) values (?, ?)
  on duplicate key update TDNum=TDNum+1;
```

## 删

```sql
update `table_dish` set TDNum=TDNum-1 where TNo=? and DId=?
delete from `table_dish` where TDNum<=0
```

## 查找

空闲桌

```sql
select TNo,TAvl from `table` where TAvl=1
```

选桌

```sql
select count(*) from `table` where TNo=? and TPass=? and TAvl=1
```

全部

```sql
select * from `dish`
```

使用**正则表达式**进行**模糊**查找

按食材名称

```sql
select * from `dish` where Did in (
  select DId from `dish_food` where FId in (
    select FId from `food` where FName like %?%
  )
)
```

按菜品名称

```sql
select * from `dish` where DName like %?%
```

小计

```sql
select `dish`.*, TDNum*DPrice from `table_dish` join `dish`
  where TNo=? and `table_dish`.DId=`dish`.DId
```

合计

```sql
select sum(TDNum*DPrice) from `table_dish` join `dish`
  where TNo=? and `table_dish`.DId=`dish`.DId
```

## 空闲

```sql
-- 更新桌空闲状态
update `table` set TAvl=TAvl^1 where TNo=?
```

## 触发器

无法订购食材**需求** $\geq$ 食材**剩余量**的

```sql
delimiter |
CREATE DEFINER=`root`@`localhost` TRIGGER `resChecktrig` BEFORE UPDATE ON `table_dish` FOR EACH ROW
BEGIN
  IF
    EXISTS(
      SELECT tmp2.FId FROM food, (
        SELECT tmp.FId, New.TDNum*tmp.FNum AS FoodConsume FROM table_dish, (
          SELECT * FROM dish_food
          WHERE dish_food.DId=NEW.DId
        ) tmp
        WHERE New.DId=tmp.DId
      ) tmp2
      WHERE tmp2.FId=food.FId AND tmp2.FoodConsume>food.FRes
    )
  THEN
    SET NEW.TDNum=NEW.TDNum-1;
  END IF;
END
|
delimiter ;
```

