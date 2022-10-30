SELECT first_name, birthday
FROM users
WHERE birthday > '19991023'
ORDER BY first_name
LIMIT 3;