create table animals.animal_type (
	id_type int generated by default as identity primary key,
	type nchar(50),
	is_wild bool
);