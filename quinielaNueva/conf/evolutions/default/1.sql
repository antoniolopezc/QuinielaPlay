# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table definicion_resultado (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  estado                    integer,
  constraint ck_definicion_resultado_estado check (estado in (0,1)),
  constraint pk_definicion_resultado primary key (id))
;

create table detalle_pronostico (
  id                        bigint not null,
  nombre                    varchar(255),
  dueño_id                  bigint,
  quiniela_id               bigint,
  constraint pk_detalle_pronostico primary key (id))
;

create table equipo (
  id                        bigint not null,
  torneo_id                 bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  escudo                    blob,
  bandera                   blob,
  final_id                  bigint,
  constraint pk_equipo primary key (id))
;

create table partido (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  lugar                     varchar(255),
  fecha                     timestamp,
  tiempo_actual             integer,
  torneo_id                 bigint,
  equipo_a_id               bigint,
  equipo_b_id               bigint,
  constraint ck_partido_tiempo_actual check (tiempo_actual in (0,1,2,3,4,5)),
  constraint pk_partido primary key (id))
;

create table porcion (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    timestamp,
  fin                       timestamp,
  torneo_id                 bigint,
  constraint pk_porcion primary key (id))
;

create table pronostico (
  id                        bigint not null,
  nombre                    varchar(255),
  dueño_id                  bigint,
  quiniela_id               bigint,
  constraint pk_pronostico primary key (id))
;

create table pronostico_detalle (
  id                        bigint not null,
  pronostico_id             bigint,
  partido_id                bigint,
  porcion_id                bigint,
  constraint pk_pronostico_detalle primary key (id))
;

create table quiniela (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    timestamp,
  fin                       timestamp,
  imagen                    blob,
  dueño_id                  bigint,
  constraint pk_quiniela primary key (id))
;

create table regla (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  clase                     varchar(255),
  parametros                varchar(255),
  constraint pk_regla primary key (id))
;

create table resultado (
  id                        bigint not null,
  pronostico_detalle_id     bigint not null,
  definicion_id             bigint,
  estado                    integer,
  resultado                 bigint,
  constraint ck_resultado_estado check (estado in (0,1,2)),
  constraint pk_resultado primary key (id))
;

create table torneo (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  descricion                varchar(255),
  inicio                    timestamp,
  fin                       timestamp,
  imagen                    blob,
  dueño_id                  bigint,
  constraint pk_torneo primary key (id))
;

create table usuario (
  id                        bigint not null,
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
create sequence definicion_resultado_seq;

create sequence detalle_pronostico_seq;

create sequence equipo_seq;

create sequence partido_seq;

create sequence porcion_seq;

create sequence pronostico_seq;

create sequence pronostico_detalle_seq;

create sequence quiniela_seq;

create sequence regla_seq;

create sequence resultado_seq;

create sequence torneo_seq;

create sequence usuario_seq;

alter table detalle_pronostico add constraint fk_detalle_pronostico_Dueño_1 foreign key (dueño_id) references usuario (id) on delete restrict on update restrict;
create index ix_detalle_pronostico_Dueño_1 on detalle_pronostico (dueño_id);
alter table detalle_pronostico add constraint fk_detalle_pronostico_Quiniela_2 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;
create index ix_detalle_pronostico_Quiniela_2 on detalle_pronostico (quiniela_id);
alter table equipo add constraint fk_equipo_torneo_3 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_equipo_torneo_3 on equipo (torneo_id);
alter table equipo add constraint fk_equipo_Final_4 foreign key (final_id) references equipo (id) on delete restrict on update restrict;
create index ix_equipo_Final_4 on equipo (final_id);
alter table partido add constraint fk_partido_Torneo_5 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_partido_Torneo_5 on partido (torneo_id);
alter table partido add constraint fk_partido_EquipoA_6 foreign key (equipo_a_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoA_6 on partido (equipo_a_id);
alter table partido add constraint fk_partido_EquipoB_7 foreign key (equipo_b_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoB_7 on partido (equipo_b_id);
alter table porcion add constraint fk_porcion_Torneo_8 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_porcion_Torneo_8 on porcion (torneo_id);
alter table pronostico add constraint fk_pronostico_Dueño_9 foreign key (dueño_id) references usuario (id) on delete restrict on update restrict;
create index ix_pronostico_Dueño_9 on pronostico (dueño_id);
alter table pronostico add constraint fk_pronostico_Quiniela_10 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;
create index ix_pronostico_Quiniela_10 on pronostico (quiniela_id);
alter table pronostico_detalle add constraint fk_pronostico_detalle_Pronost_11 foreign key (pronostico_id) references pronostico (id) on delete restrict on update restrict;
create index ix_pronostico_detalle_Pronost_11 on pronostico_detalle (pronostico_id);
alter table pronostico_detalle add constraint fk_pronostico_detalle_Partido_12 foreign key (partido_id) references partido (id) on delete restrict on update restrict;
create index ix_pronostico_detalle_Partido_12 on pronostico_detalle (partido_id);
alter table pronostico_detalle add constraint fk_pronostico_detalle_Porcion_13 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;
create index ix_pronostico_detalle_Porcion_13 on pronostico_detalle (porcion_id);
alter table quiniela add constraint fk_quiniela_Dueño_14 foreign key (dueño_id) references usuario (id) on delete restrict on update restrict;
create index ix_quiniela_Dueño_14 on quiniela (dueño_id);
alter table resultado add constraint fk_resultado_pronostico_detal_15 foreign key (pronostico_detalle_id) references pronostico_detalle (id) on delete restrict on update restrict;
create index ix_resultado_pronostico_detal_15 on resultado (pronostico_detalle_id);
alter table resultado add constraint fk_resultado_Definicion_16 foreign key (definicion_id) references definicion_resultado (id) on delete restrict on update restrict;
create index ix_resultado_Definicion_16 on resultado (definicion_id);
alter table torneo add constraint fk_torneo_Dueño_17 foreign key (dueño_id) references usuario (id) on delete restrict on update restrict;
create index ix_torneo_Dueño_17 on torneo (dueño_id);



alter table porcion_partido add constraint fk_porcion_partido_porcion_01 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;

alter table porcion_partido add constraint fk_porcion_partido_partido_02 foreign key (partido_id) references partido (id) on delete restrict on update restrict;

alter table quiniela_usuario add constraint fk_quiniela_usuario_quiniela_01 foreign key (quiniela_id) references quiniela (id) on delete restrict on update restrict;

alter table quiniela_usuario add constraint fk_quiniela_usuario_usuario_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_regla_02 foreign key (regla_id) references regla (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_usuario_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists definicion_resultado;

drop table if exists detalle_pronostico;

drop table if exists equipo;

drop table if exists partido;

drop table if exists porcion;

drop table if exists porcion_partido;

drop table if exists pronostico;

drop table if exists pronostico_detalle;

drop table if exists quiniela;

drop table if exists quiniela_usuario;

drop table if exists regla;

drop table if exists resultado;

drop table if exists torneo;

drop table if exists torneo_regla;

drop table if exists torneo_usuario;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists definicion_resultado_seq;

drop sequence if exists detalle_pronostico_seq;

drop sequence if exists equipo_seq;

drop sequence if exists partido_seq;

drop sequence if exists porcion_seq;

drop sequence if exists pronostico_seq;

drop sequence if exists pronostico_detalle_seq;

drop sequence if exists quiniela_seq;

drop sequence if exists regla_seq;

drop sequence if exists resultado_seq;

drop sequence if exists torneo_seq;

drop sequence if exists usuario_seq;

