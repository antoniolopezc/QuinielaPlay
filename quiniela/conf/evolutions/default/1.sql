# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table equipo (
  id                        bigint not null,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  escudo                    blob,
  bandera                   blob,
  torneo_id                 bigint,
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
  resultado_equipo_a_id     bigint,
  equipo_a_id               bigint,
  resultado_equipo_b_id     bigint,
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
  partido_id                bigint,
  porcion_id                bigint,
  equipo_id                 bigint,
  nombre                    varchar(255),
  nombre_corto              varchar(255),
  abreviatura               varchar(255),
  estado                    integer,
  resultado_equipo_id       bigint,
  resultado_numerico        bigint,
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
  alias                     varchar(255),
  correo                    varchar(255),
  clave                     varchar(255),
  constraint uq_usuario_alias unique (alias),
  constraint pk_usuario primary key (id))
;


create table porcion_partido (
  porcion_id                     bigint not null,
  partido_id                     bigint not null,
  constraint pk_porcion_partido primary key (porcion_id, partido_id))
;

create table porcion_regla (
  porcion_id                     bigint not null,
  regla_id                       bigint not null,
  constraint pk_porcion_regla primary key (porcion_id, regla_id))
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
create sequence equipo_seq;

create sequence partido_seq;

create sequence porcion_seq;

create sequence regla_seq;

create sequence resultado_seq;

create sequence torneo_seq;

create sequence usuario_seq;

alter table equipo add constraint fk_equipo_Torneo_1 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_equipo_Torneo_1 on equipo (torneo_id);
alter table partido add constraint fk_partido_Torneo_2 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_partido_Torneo_2 on partido (torneo_id);
alter table partido add constraint fk_partido_ResultadoEquipoA_3 foreign key (resultado_equipo_a_id) references resultado (id) on delete restrict on update restrict;
create index ix_partido_ResultadoEquipoA_3 on partido (resultado_equipo_a_id);
alter table partido add constraint fk_partido_EquipoA_4 foreign key (equipo_a_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoA_4 on partido (equipo_a_id);
alter table partido add constraint fk_partido_ResultadoEquipoB_5 foreign key (resultado_equipo_b_id) references resultado (id) on delete restrict on update restrict;
create index ix_partido_ResultadoEquipoB_5 on partido (resultado_equipo_b_id);
alter table partido add constraint fk_partido_EquipoB_6 foreign key (equipo_b_id) references equipo (id) on delete restrict on update restrict;
create index ix_partido_EquipoB_6 on partido (equipo_b_id);
alter table porcion add constraint fk_porcion_Torneo_7 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;
create index ix_porcion_Torneo_7 on porcion (torneo_id);
alter table resultado add constraint fk_resultado_Partido_8 foreign key (partido_id) references partido (id) on delete restrict on update restrict;
create index ix_resultado_Partido_8 on resultado (partido_id);
alter table resultado add constraint fk_resultado_Porcion_9 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;
create index ix_resultado_Porcion_9 on resultado (porcion_id);
alter table resultado add constraint fk_resultado_Equipo_10 foreign key (equipo_id) references equipo (id) on delete restrict on update restrict;
create index ix_resultado_Equipo_10 on resultado (equipo_id);
alter table resultado add constraint fk_resultado_ResultadoEquipo_11 foreign key (resultado_equipo_id) references equipo (id) on delete restrict on update restrict;
create index ix_resultado_ResultadoEquipo_11 on resultado (resultado_equipo_id);
alter table torneo add constraint fk_torneo_Dueño_12 foreign key (dueño_id) references usuario (id) on delete restrict on update restrict;
create index ix_torneo_Dueño_12 on torneo (dueño_id);



alter table porcion_partido add constraint fk_porcion_partido_porcion_01 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;

alter table porcion_partido add constraint fk_porcion_partido_partido_02 foreign key (partido_id) references partido (id) on delete restrict on update restrict;

alter table porcion_regla add constraint fk_porcion_regla_porcion_01 foreign key (porcion_id) references porcion (id) on delete restrict on update restrict;

alter table porcion_regla add constraint fk_porcion_regla_regla_02 foreign key (regla_id) references regla (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_regla add constraint fk_torneo_regla_regla_02 foreign key (regla_id) references regla (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_torneo_01 foreign key (torneo_id) references torneo (id) on delete restrict on update restrict;

alter table torneo_usuario add constraint fk_torneo_usuario_usuario_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists equipo;

drop table if exists partido;

drop table if exists porcion;

drop table if exists porcion_partido;

drop table if exists porcion_regla;

drop table if exists regla;

drop table if exists resultado;

drop table if exists torneo;

drop table if exists torneo_regla;

drop table if exists torneo_usuario;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists equipo_seq;

drop sequence if exists partido_seq;

drop sequence if exists porcion_seq;

drop sequence if exists regla_seq;

drop sequence if exists resultado_seq;

drop sequence if exists torneo_seq;

drop sequence if exists usuario_seq;

