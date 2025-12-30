-- Project Name : Liberty
-- Date/Time    : 2025/12/30 15:20:36
-- Author       : DTSVN
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- 販売者プロフィール
drop table if exists M_SELLER_PROFILE cascade;

create table M_SELLER_PROFILE (
  SELLER_ID bigserial not null
  , USER_ID bigint not null
  , SHOP_NAME text not null
  , DESCRIPTION text
  , VERIFIED_FLAG varchar(20)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_SELLER_PROFILE_PKC primary key (SELLER_ID)
) ;

-- ユーザ
drop table if exists M_USER cascade;

create table M_USER (
  USER_ID bigserial not null
  , ROLE_ID bigint not null
  , EMAIL varchar(256)
  , PASSWORD_HASH text
  , FULL_NAME text
  , PHONE varchar(15)
  , STATUS varchar(20)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_USER_PKC primary key (USER_ID)
) ;

-- カートアイテム
drop table if exists M_CART_ITEM cascade;

create table M_CART_ITEM (
  CART_ITEM_ID bigserial not null
  , CART_ID bigint not null
  , FOOD_ID bigint not null
  , QUANTITY integer
  , PRICE decimal(10,0)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_CART_ITEM_PKC primary key (CART_ITEM_ID)
) ;

-- カート
drop table if exists M_CART cascade;

create table M_CART (
  CART_ID bigserial not null
  , UPDATED_AT timestamp
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_CART_PKC primary key (CART_ID)
) ;

-- 住所
drop table if exists M_ADDRESS cascade;

create table M_ADDRESS (
  ADDRESS_ID bigserial not null
  , RECEIVER_NAME text
  , PHONE varchar(15)
  , ADDRESS_DETAIL text
  , IS_DEFAULT boolean
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_ADDRESS_PKC primary key (ADDRESS_ID)
) ;

-- 注文アイテム
drop table if exists M_ORDER_ITEM cascade;

create table M_ORDER_ITEM (
  ORDER_ITEM_ID bigserial not null
  , ORDER_ID bigint not null
  , FOOD_ID bigint not null
  , QUANTITY integer
  , PRICE decimal(10,0)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_ORDER_ITEM_PKC primary key (ORDER_ITEM_ID)
) ;

-- 注文
drop table if exists M_ORDER cascade;

create table M_ORDER (
  ORDER_ID bigserial not null
  , ADDRESS_ID bigint
  , TOTAL_AMOUNT decimal(10,0)
  , ORDER_STATUS varchar(20)
  , CREATED_AT timestamp
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_ORDER_PKC primary key (ORDER_ID)
) ;

-- 支払い
drop table if exists M_PAYMENT cascade;

create table M_PAYMENT (
  PAYMENT_ID bigserial not null
  , ORDER_ID bigint not null
  , PAYMENT_METHOD varchar(20)
  , PAYMENT_STATUS varchar(20)
  , TRANSACTION_CODE varchar(20)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_PAYMENT_PKC primary key (PAYMENT_ID)
) ;

-- レビュー
drop table if exists M_REVIEW cascade;

create table M_REVIEW (
  REVIEW_ID bigserial not null
  , FOOD_ID bigint not null
  , USER_ID bigint
  , RATING integer
  , COMMENT text
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_REVIEW_PKC primary key (REVIEW_ID)
) ;

-- 役割
drop table if exists M_ROLE cascade;

create table M_ROLE (
  ROLE_ID bigserial not null
  , ROLE_CODE varchar(20)
  , ROLE_NAME text
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_ROLE_PKC primary key (ROLE_ID)
) ;

-- 食品カテゴリー
drop table if exists M_FOOD_CATEGORY cascade;

create table M_FOOD_CATEGORY (
  CATEGORY_ID bigserial not null
  , CATEGORY_NAME text
  , PARENT_CATEGORY_ID bigint
  , STATUS varchar(20)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_FOOD_CATEGORY_PKC primary key (CATEGORY_ID)
) ;

-- 食品
drop table if exists M_FOOD cascade;

create table M_FOOD (
  FOOD_ID bigserial not null
  , SELLER_ID bigint
  , CATEGORY_ID bigint not null
  , FOOD_NAME text
  , DESCRIPTION text
  , PRICE decimal(10,0)
  , DISCOUNT_PRICE decimal(10,0)
  , QUANTITY integer
  , STATUS varchar(20)
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_FOOD_PKC primary key (FOOD_ID)
) ;

-- 食品画像
drop table if exists M_FOOD_IMAGE cascade;

create table M_FOOD_IMAGE (
  IMAGE_ID bigserial not null
  , FOOD_ID bigint
  , IMAGE_URL text
  , IS_MAIN boolean
  , SORT_ORDER integer
  , DELETED_FLAG char(1) default '0' not null
  , UNUSABLE_FLAG char(1) default '0' not null
  , UPDATE_COUNT integer default 1 not null
  , CREATE_USER_ID text
  , CREATE_USER_NAME text
  , CREATE_TIMESTAMP timestamp
  , UPDATE_USER_ID text
  , UPDATE_USER_NAME text
  , UPDATE_TIMESTAMP timestamp
  , constraint M_FOOD_IMAGE_PKC primary key (IMAGE_ID)
) ;

alter table M_PAYMENT
  add constraint M_PAYMENT_FK1 foreign key (ORDER_ID) references M_ORDER(ORDER_ID);

alter table M_ORDER
  add constraint M_ORDER_FK1 foreign key (ADDRESS_ID) references M_ADDRESS(ADDRESS_ID);

alter table M_ORDER_ITEM
  add constraint M_ORDER_ITEM_FK1 foreign key (FOOD_ID) references M_FOOD(FOOD_ID);

alter table M_CART_ITEM
  add constraint M_CART_ITEM_FK1 foreign key (FOOD_ID) references M_FOOD(FOOD_ID);

alter table M_REVIEW
  add constraint M_REVIEW_FK1 foreign key (FOOD_ID) references M_FOOD(FOOD_ID);

alter table M_SELLER_PROFILE
  add constraint M_SELLER_PROFILE_FK1 foreign key (USER_ID) references M_USER(USER_ID);

alter table M_USER
  add constraint M_USER_FK1 foreign key (ROLE_ID) references M_ROLE(ROLE_ID);

alter table M_CART_ITEM
  add constraint M_CART_ITEM_FK2 foreign key (CART_ID) references M_CART(CART_ID);

alter table M_ORDER_ITEM
  add constraint M_ORDER_ITEM_FK2 foreign key (ORDER_ID) references M_ORDER(ORDER_ID);

alter table M_FOOD
  add constraint M_FOOD_FK1 foreign key (CATEGORY_ID) references M_FOOD_CATEGORY(CATEGORY_ID);

comment on table M_SELLER_PROFILE is '販売者プロフィール';
comment on column M_SELLER_PROFILE.SELLER_ID is '販売者プロフィールID';
comment on column M_SELLER_PROFILE.USER_ID is 'ユーザID';
comment on column M_SELLER_PROFILE.SHOP_NAME is 'ショップ名';
comment on column M_SELLER_PROFILE.DESCRIPTION is '備考';
comment on column M_SELLER_PROFILE.VERIFIED_FLAG is '検証済みフラグ';
comment on column M_SELLER_PROFILE.SORT_ORDER is '並び順';
comment on column M_SELLER_PROFILE.DELETED_FLAG is '削除済みフラグ';
comment on column M_SELLER_PROFILE.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_SELLER_PROFILE.UPDATE_COUNT is '更新回数';
comment on column M_SELLER_PROFILE.CREATE_USER_ID is '作成ユーザID';
comment on column M_SELLER_PROFILE.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_SELLER_PROFILE.CREATE_TIMESTAMP is '作成日時';
comment on column M_SELLER_PROFILE.UPDATE_USER_ID is '更新ユーザID';
comment on column M_SELLER_PROFILE.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_SELLER_PROFILE.UPDATE_TIMESTAMP is '更新日時';

comment on table M_USER is 'ユーザ';
comment on column M_USER.USER_ID is 'ユーザID';
comment on column M_USER.ROLE_ID is '役割ID';
comment on column M_USER.EMAIL is 'メール';
comment on column M_USER.PASSWORD_HASH is 'パスワード';
comment on column M_USER.FULL_NAME is 'ユーザ名称';
comment on column M_USER.PHONE is '電話番号';
comment on column M_USER.STATUS is 'ステータス';
comment on column M_USER.SORT_ORDER is '並び順';
comment on column M_USER.DELETED_FLAG is '削除済みフラグ';
comment on column M_USER.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_USER.UPDATE_COUNT is '更新回数';
comment on column M_USER.CREATE_USER_ID is '作成ユーザID';
comment on column M_USER.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_USER.CREATE_TIMESTAMP is '作成日時';
comment on column M_USER.UPDATE_USER_ID is '更新ユーザID';
comment on column M_USER.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_USER.UPDATE_TIMESTAMP is '更新日時';

comment on table M_CART_ITEM is 'カートアイテム';
comment on column M_CART_ITEM.CART_ITEM_ID is 'カートアイテムID';
comment on column M_CART_ITEM.CART_ID is 'カートID';
comment on column M_CART_ITEM.FOOD_ID is '食品ID';
comment on column M_CART_ITEM.QUANTITY is '数量';
comment on column M_CART_ITEM.PRICE is '価格';
comment on column M_CART_ITEM.SORT_ORDER is '並び順';
comment on column M_CART_ITEM.DELETED_FLAG is '削除済みフラグ';
comment on column M_CART_ITEM.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_CART_ITEM.UPDATE_COUNT is '更新回数';
comment on column M_CART_ITEM.CREATE_USER_ID is '作成ユーザID';
comment on column M_CART_ITEM.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_CART_ITEM.CREATE_TIMESTAMP is '作成日時';
comment on column M_CART_ITEM.UPDATE_USER_ID is '更新ユーザID';
comment on column M_CART_ITEM.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_CART_ITEM.UPDATE_TIMESTAMP is '更新日時';

comment on table M_CART is 'カート';
comment on column M_CART.CART_ID is 'カートID';
comment on column M_CART.UPDATED_AT is '更新日時';
comment on column M_CART.SORT_ORDER is '並び順';
comment on column M_CART.DELETED_FLAG is '削除済みフラグ';
comment on column M_CART.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_CART.UPDATE_COUNT is '更新回数';
comment on column M_CART.CREATE_USER_ID is '作成ユーザID';
comment on column M_CART.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_CART.CREATE_TIMESTAMP is '作成日時';
comment on column M_CART.UPDATE_USER_ID is '更新ユーザID';
comment on column M_CART.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_CART.UPDATE_TIMESTAMP is '更新日時';

comment on table M_ADDRESS is '住所';
comment on column M_ADDRESS.ADDRESS_ID is '住所ID';
comment on column M_ADDRESS.RECEIVER_NAME is '受信者名';
comment on column M_ADDRESS.PHONE is '電話番号';
comment on column M_ADDRESS.ADDRESS_DETAIL is '住所詳細';
comment on column M_ADDRESS.IS_DEFAULT is 'デフォルト';
comment on column M_ADDRESS.SORT_ORDER is '並び順';
comment on column M_ADDRESS.DELETED_FLAG is '削除済みフラグ';
comment on column M_ADDRESS.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_ADDRESS.UPDATE_COUNT is '更新回数';
comment on column M_ADDRESS.CREATE_USER_ID is '作成ユーザID';
comment on column M_ADDRESS.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_ADDRESS.CREATE_TIMESTAMP is '作成日時';
comment on column M_ADDRESS.UPDATE_USER_ID is '更新ユーザID';
comment on column M_ADDRESS.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_ADDRESS.UPDATE_TIMESTAMP is '更新日時';

comment on table M_ORDER_ITEM is '注文アイテム';
comment on column M_ORDER_ITEM.ORDER_ITEM_ID is '注文アイテムID';
comment on column M_ORDER_ITEM.ORDER_ID is '注文ID';
comment on column M_ORDER_ITEM.FOOD_ID is '食品ID';
comment on column M_ORDER_ITEM.QUANTITY is '数量';
comment on column M_ORDER_ITEM.PRICE is '料金';
comment on column M_ORDER_ITEM.SORT_ORDER is '並び順';
comment on column M_ORDER_ITEM.DELETED_FLAG is '削除済みフラグ';
comment on column M_ORDER_ITEM.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_ORDER_ITEM.UPDATE_COUNT is '更新回数';
comment on column M_ORDER_ITEM.CREATE_USER_ID is '作成ユーザID';
comment on column M_ORDER_ITEM.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_ORDER_ITEM.CREATE_TIMESTAMP is '作成日時';
comment on column M_ORDER_ITEM.UPDATE_USER_ID is '更新ユーザID';
comment on column M_ORDER_ITEM.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_ORDER_ITEM.UPDATE_TIMESTAMP is '更新日時';

comment on table M_ORDER is '注文';
comment on column M_ORDER.ORDER_ID is '注文ID';
comment on column M_ORDER.ADDRESS_ID is '住所ID';
comment on column M_ORDER.TOTAL_AMOUNT is '合計金額';
comment on column M_ORDER.ORDER_STATUS is '注文ステータス';
comment on column M_ORDER.CREATED_AT is '作成日時';
comment on column M_ORDER.SORT_ORDER is '並び順';
comment on column M_ORDER.DELETED_FLAG is '削除済みフラグ';
comment on column M_ORDER.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_ORDER.UPDATE_COUNT is '更新回数';
comment on column M_ORDER.CREATE_USER_ID is '作成ユーザID';
comment on column M_ORDER.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_ORDER.CREATE_TIMESTAMP is '作成日時';
comment on column M_ORDER.UPDATE_USER_ID is '更新ユーザID';
comment on column M_ORDER.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_ORDER.UPDATE_TIMESTAMP is '更新日時';

comment on table M_PAYMENT is '支払い';
comment on column M_PAYMENT.PAYMENT_ID is '支払いID';
comment on column M_PAYMENT.ORDER_ID is '注文ID';
comment on column M_PAYMENT.PAYMENT_METHOD is '支払方法';
comment on column M_PAYMENT.PAYMENT_STATUS is '支払ステータス';
comment on column M_PAYMENT.TRANSACTION_CODE is '取引コード';
comment on column M_PAYMENT.SORT_ORDER is '並び順';
comment on column M_PAYMENT.DELETED_FLAG is '削除済みフラグ';
comment on column M_PAYMENT.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_PAYMENT.UPDATE_COUNT is '更新回数';
comment on column M_PAYMENT.CREATE_USER_ID is '作成ユーザID';
comment on column M_PAYMENT.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_PAYMENT.CREATE_TIMESTAMP is '作成日時';
comment on column M_PAYMENT.UPDATE_USER_ID is '更新ユーザID';
comment on column M_PAYMENT.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_PAYMENT.UPDATE_TIMESTAMP is '更新日時';

comment on table M_REVIEW is 'レビュー';
comment on column M_REVIEW.REVIEW_ID is 'レビューID';
comment on column M_REVIEW.FOOD_ID is '食品ID';
comment on column M_REVIEW.USER_ID is 'ユーザID';
comment on column M_REVIEW.RATING is '評価';
comment on column M_REVIEW.COMMENT is 'コメント';
comment on column M_REVIEW.SORT_ORDER is '並び順';
comment on column M_REVIEW.DELETED_FLAG is '削除済みフラグ';
comment on column M_REVIEW.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_REVIEW.UPDATE_COUNT is '更新回数';
comment on column M_REVIEW.CREATE_USER_ID is '作成ユーザID';
comment on column M_REVIEW.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_REVIEW.CREATE_TIMESTAMP is '作成日時';
comment on column M_REVIEW.UPDATE_USER_ID is '更新ユーザID';
comment on column M_REVIEW.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_REVIEW.UPDATE_TIMESTAMP is '更新日時';

comment on table M_ROLE is '役割';
comment on column M_ROLE.ROLE_ID is '役割ID';
comment on column M_ROLE.ROLE_CODE is '役割コード';
comment on column M_ROLE.ROLE_NAME is '役割名';
comment on column M_ROLE.SORT_ORDER is '並び順';
comment on column M_ROLE.DELETED_FLAG is '削除済みフラグ';
comment on column M_ROLE.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_ROLE.UPDATE_COUNT is '更新回数';
comment on column M_ROLE.CREATE_USER_ID is '作成ユーザID';
comment on column M_ROLE.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_ROLE.CREATE_TIMESTAMP is '作成日時';
comment on column M_ROLE.UPDATE_USER_ID is '更新ユーザID';
comment on column M_ROLE.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_ROLE.UPDATE_TIMESTAMP is '更新日時';

comment on table M_FOOD_CATEGORY is '食品カテゴリー';
comment on column M_FOOD_CATEGORY.CATEGORY_ID is 'カテゴリーID';
comment on column M_FOOD_CATEGORY.CATEGORY_NAME is 'カテゴリー名称';
comment on column M_FOOD_CATEGORY.PARENT_CATEGORY_ID is '親カテゴリーID';
comment on column M_FOOD_CATEGORY.STATUS is 'ステータス';
comment on column M_FOOD_CATEGORY.SORT_ORDER is '並び順';
comment on column M_FOOD_CATEGORY.DELETED_FLAG is '削除済みフラグ';
comment on column M_FOOD_CATEGORY.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_FOOD_CATEGORY.UPDATE_COUNT is '更新回数';
comment on column M_FOOD_CATEGORY.CREATE_USER_ID is '作成ユーザID';
comment on column M_FOOD_CATEGORY.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_FOOD_CATEGORY.CREATE_TIMESTAMP is '作成日時';
comment on column M_FOOD_CATEGORY.UPDATE_USER_ID is '更新ユーザID';
comment on column M_FOOD_CATEGORY.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_FOOD_CATEGORY.UPDATE_TIMESTAMP is '更新日時';

comment on table M_FOOD is '食品';
comment on column M_FOOD.FOOD_ID is '食品ID';
comment on column M_FOOD.SELLER_ID is '販売者ID';
comment on column M_FOOD.CATEGORY_ID is 'カテゴリーID';
comment on column M_FOOD.FOOD_NAME is '食品名称';
comment on column M_FOOD.DESCRIPTION is '説明';
comment on column M_FOOD.PRICE is '価格';
comment on column M_FOOD.DISCOUNT_PRICE is '割引価格';
comment on column M_FOOD.QUANTITY is '食品数';
comment on column M_FOOD.STATUS is 'ステータス';
comment on column M_FOOD.SORT_ORDER is '並び順';
comment on column M_FOOD.DELETED_FLAG is '削除済みフラグ';
comment on column M_FOOD.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_FOOD.UPDATE_COUNT is '更新回数';
comment on column M_FOOD.CREATE_USER_ID is '作成ユーザID';
comment on column M_FOOD.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_FOOD.CREATE_TIMESTAMP is '作成日時';
comment on column M_FOOD.UPDATE_USER_ID is '更新ユーザID';
comment on column M_FOOD.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_FOOD.UPDATE_TIMESTAMP is '更新日時';

comment on table M_FOOD_IMAGE is '食品画像';
comment on column M_FOOD_IMAGE.IMAGE_ID is '画像ID';
comment on column M_FOOD_IMAGE.FOOD_ID is '食品ID';
comment on column M_FOOD_IMAGE.IMAGE_URL is '画像URL';
comment on column M_FOOD_IMAGE.IS_MAIN is 'メイン';
comment on column M_FOOD_IMAGE.SORT_ORDER is '並び順';
comment on column M_FOOD_IMAGE.DELETED_FLAG is '削除済みフラグ';
comment on column M_FOOD_IMAGE.UNUSABLE_FLAG is '使用不可フラグ';
comment on column M_FOOD_IMAGE.UPDATE_COUNT is '更新回数';
comment on column M_FOOD_IMAGE.CREATE_USER_ID is '作成ユーザID';
comment on column M_FOOD_IMAGE.CREATE_USER_NAME is '作成ユーザ名';
comment on column M_FOOD_IMAGE.CREATE_TIMESTAMP is '作成日時';
comment on column M_FOOD_IMAGE.UPDATE_USER_ID is '更新ユーザID';
comment on column M_FOOD_IMAGE.UPDATE_USER_NAME is '更新ユーザ名';
comment on column M_FOOD_IMAGE.UPDATE_TIMESTAMP is '更新日時';

