# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table definicion_resultado (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  tipo                      integer,
  constraint ck_definicion_resultado_tipo check (tipo in (0,1)),
  constraint pk_definicion_resultado primary key (id))
;

create table equipo (
  id                        bigint auto_increment not null,
  torneo_id                 bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  caculable                 tinyint(1) default 0,
  escudo                    varchar(255),
  bandera                   varchar(255),
  final_id                  bigint,
  constraint pk_equipo primary key (id))
;

create table partido (
  id                        bigint auto_increment not null,
  torneo_id                 bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  lugar                     varchar(255),
  fecha                     datetime,
  tiempo_actual             integer,
  equipo_a_id               bigint,
  equipo_b_id               bigint,
  constraint ck_partido_tiempo_actual check (tiempo_actual in (0,1,2,3,4,5)),
  constraint pk_partido primary key (id))
;

create table porcion (
  id                        bigint auto_increment not null,
  torneo_id                 bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    datetime,
  fin                       datetime,
  constraint pk_porcion primary key (id))
;

create table pronostico (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  aprobado                  tinyint(1) default 0,
  propietario_id            bigint,
  quiniela_id               bigint,
  constraint pk_pronostico primary key (id))
;

create table punto (
  id                        bigint auto_increment not null,
  valor                     bigint,
  maximo                    bigint,
  estado                    integer,
  referencia_regla          varchar(255),
  resultado_id              bigint,
  partido_id                bigint,
  porcion_id                bigint,
  pronostico_id             bigint,
  constraint ck_punto_estado check (estado in (0,1,2)),
  constraint pk_punto primary key (id))
;

create table quiniela (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    datetime,
  fin                       datetime,
  imagen                    longblob,
  torneo_id                 bigint,
  propietario_id            bigint,
  constraint pk_quiniela primary key (id))
;

create table regla (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  clase                     varchar(255),
  parametros                longtext,
  constraint pk_regla primary key (id))
;

create table resultado (
  id                        bigint auto_increment not null,
  definicion_id             bigint,
  estado                    integer,
  entero                    bigint,
  equipo_id                 bigint,
  partido_id                bigint,
  porcion_id                bigint,
  constraint ck_resultado_estado check (estado in (0,1,2)),
  constraint pk_resultado primary key (id))
;

create table resultado_pronostico (
  id                        bigint auto_increment not null,
  pronostico_id             bigint not null,
  resultado_id              bigint,
  entero                    bigint,
  equipo_id                 bigint,
  constraint pk_resultado_pronostico primary key (id))
;

create table torneo (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    datetime,
  fin                       datetime,
  imagen                    longblob,
  propietario_id            bigint,
  constraint pk_torneo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  nombre                    varchar(255),
  avatar                    varchar(255),
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id))
;


create table porcion_partido (
  porcion_id                     bigint not null,
  partido_id                     bigint not null,
  constraint pk_porcion_partido primary key (porcion_id, partido_id))
;

create table quiniela_regla (
  quiniela_id                    bigint not null,
  regla_id                       bigint not null,
  constraint pk_quiniela_regla primary key (quiniela_id, regla_id))
;

create table quiniela_usuario (
  quiniela_id                    bigint not null,
  usuario_id                     bigint not null,
  constraint pk_quiniela_usuario primary key (quiniela_id, usuario_id))
;

create table torneo_regla (
  torneo_id                      bigint not null,
  regla_id                       bigint not null,
  constraint pk_torneo_regla primary key (torneo_id, regla_id))
;

create table torneo_usuario (
  torneo_id                      bigint not null,
  usuario_id                     bigint not null,
  constraint pk_torneo_usuario primary key (torneo_id, usuario_id))
;
alter table equipo add constraint fk_equipo_torneo_1 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_equipo_torneo_1 on equipo (torneo_id);
alter table equipo add constraint fk_equipo_Final_2 foreign key (final_id) references equipo (id) on delete restrict on update restrict;
create index ix_equipo_Final_2 on equipo (final_id);
alter table partido add constraint fk_partido_torneo_3 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_partido_torneo_3 on partido (torneo_id);
alter table partido add constraint fk_partido_EquipoA_4 foreign key (equipo_a_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoA_4 on partido (equipo_a_id);
alter table partido add constraint fk_partido_EquipoB_5 foreign key (equipo_b_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoB_5 on partido (equipo_b_id);
alter table porcion add constraint fk_porcion_torneo_6 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_porcion_torneo_6 on porcion (torneo_id);
alter table pronostico add constraint fk_pronostico_Propietario_7 foreign key (propietario_id) references usuario (id) on delete restrict on update restrict;
create index ix_pronostico_Propietario_7 on pronostico (propietario_id);
alter table pronostico add constraint fk_pronostico_Quiniela_8 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;
create index ix_pronostico_Quiniela_8 on pronostico (quiniela_id);
alter table punto add constraint fk_punto_Resultado_9 foreign key (resultado_id) references resultado (id) on delete restrict on update restrict;
create index ix_punto_Resultado_9 on punto (resultado_id);
alter table punto add constraint fk_punto_Partido_10 foreign key (partido_id) references partido (id) on delete restrict on update restrict;
create index ix_punto_Partido_10 on punto (partido_id);
alter table punto add constraint fk_punto_Porcion_11 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;
create index ix_punto_Porcion_11 on punto (porcion_id);
alter table punto add constraint fk_punto_Pronostico_12 foreign key (pronostico_id) references pronostico (id) on delete restrict on update restrict;
create index ix_punto_Pronostico_12 on punto (pronostico_id);
alter table quiniela add constraint fk_quiniela_Torneo_13 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_quiniela_Torneo_13 on quiniela (torneo_id);
alter table quiniela add constraint fk_quiniela_Propietario_14 foreign key (propietario_id) references usuario (id) on delete restrict on update restrict;
create index ix_quiniela_Propietario_14 on quiniela (propietario_id);
alter table resultado add constraint fk_resultado_Definicion_15 foreign key (definicion_id) references definicion_resultado (id) on delete restrict on update restrict;
create index ix_resultado_Definicion_15 on resultado (definicion_id);
alter table resultado add constraint fk_resultado_Equipo_16 foreign key (equipo_id) references equipo (id) on delete restrict on update restrict;
create index ix_resultado_Equipo_16 on resultado (equipo_id);
alter table resultado add constraint fk_resultado_Partido_17 foreign key (partido_id) references partido (id) on delete restrict on update restrict;
create index ix_resultado_Partido_17 on resultado (partido_id);
alter table resultado add constraint fk_resultado_Porcion_18 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;
create index ix_resultado_Porcion_18 on resultado (porcion_id);
alter table resultado_pronostico add constraint fk_resultado_pronostico_pronostico_19 foreign key (pronostico_id) references pronostico (id) on delete restrict on update restrict;
create index ix_resultado_pronostico_pronostico_19 on resultado_pronostico (pronostico_id);
alter table resultado_pronostico add constraint fk_resultado_pronostico_Resultado_20 foreign key (resultado_id) references resultado (id) on delete restrict on update restrict;
create index ix_resultado_pronostico_Resultado_20 on resultado_pronostico (resultado_id);
alter table resultado_pronostico add constraint fk_resultado_pronostico_Equipo_21 foreign key (equipo_id) references equipo (id) on delete restrict on update restrict;
create index ix_resultado_pronostico_Equipo_21 on resultado_pronostico (equipo_id);
alter table torneo add constraint fk_torneo_Propietario_22 foreign key (propietario_id) references usuario (id) on delete restrict on update restrict;
create index ix_torneo_Propietario_22 on torneo (propietario_id);



alter table porcion_partido add constraint fk_porcion_partido_porcion_01 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;

alter table porcion_partido add constraint fk_porcion_partido_partido_02 foreign key (partido_id) references partido (id) on delete restrict on update restrict;

alter table quiniela_regla add constraint fk_quiniela_regla_quiniela_01 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;

alter table quiniela_regla add constraint fk_quiniela_regla_regla_02 foreign key (regla_id) references regla (id) on delete restrict on update restrict;

alter table quiniela_usuario add constraint fk_quiniela_usuario_quiniela_01 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;

alter table quiniela_usuario add constraint fk_quiniela_usuario_usuario_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_regla_02 foreign key (regla_id) references regla (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_usuario_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table definicion_resultado;

drop table equipo;

drop table partido;

drop table porcion;

drop table porcion_partido;

drop table pronostico;

drop table punto;

drop table quiniela;

drop table quiniela_regla;

drop table quiniela_usuario;

drop table regla;

drop table resultado;

drop table resultado_pronostico;

drop table torneo;

drop table torneo_regla;

drop table torneo_usuario;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

