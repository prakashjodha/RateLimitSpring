INSERT INTO user_detials (first_name, last_name) VALUES
  ('Mark', 'test'),
  ('Deja', 'test'),
  ('Sam', 'test');
 INSERT INTO api (api_url, status) VALUES
  ('/api/v1/developers', '1'),
  ('/api/v1/organizations', '1'),
  ('/api/v1/test', '1');
 INSERT INTO user_api_rule (user_id,api_id,token, status) VALUES
  (1,1,100, '1'),
  (1,2,250, '1'),
  (2,1,50, '1'),
  (2,2,500, '1');
 