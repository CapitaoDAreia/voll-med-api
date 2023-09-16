alter table appointments add active tinyint;
update appointments set active = 1;