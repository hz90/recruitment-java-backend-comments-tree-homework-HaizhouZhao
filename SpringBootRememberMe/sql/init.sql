CREATE TABLE t_content( 
  id INTEGER PRIMARY KEY AUTOINCREMENT
  , fid INTEGER
  , userid INTEGER NOT NULL
  , content varchar (255) NOT NULL
  , createdate timestamp not null default (datetime('now', 'localtime'))
)

CREATE TABLE t_user( 
  id INTEGER PRIMARY KEY AUTOINCREMENT
  , username varchar (20) NOT NULL
  , email varchar (255) NOT NULL
  , password varchar (255) NOT NULL
)