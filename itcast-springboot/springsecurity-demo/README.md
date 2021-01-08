### 授权所涉及到的相关表
```sql

create table t_role(
	id			int(12) not null auto_increment,
	role_name 	varchar(60) not null,
	note 		varchar(256),
	primary key(id)
);

create table t_user(
	id			int(12) not null auto_increment,
	user_name 	varchar(60) not null,
	pwd			varchar(100) not null,
	available	int(1) DEFAULT 1 CHECK(available IN (0,1)),
	note 		varchar(256),
	primary key(id),
	unique(user_name)
);

create table t_user_role(
	id			int(12) not null auto_increment,
	role_id		int(12) not null,
	user_id 	int(12) not null,
	primary key(id),
	unique(role_id,user_id)
);

```
