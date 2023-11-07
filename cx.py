import cx_Oracle
cx_Oracle.init_oracle_client(lib_dir="/Users/jdsm/Desktop/sistransProyecto/faker/instantclient_19_8")
from faker import Faker
import random

# Create a Faker instance
fake = Faker()

# Create a connection to the Oracle database
dsn = cx_Oracle.makedsn('fn4.oracle.virtual.uniandes.edu.co', '1521', 'PROD')
conn = cx_Oracle.connect(user='ISIS2304E04202320', password='tBFvqCzpZaWY', dsn=dsn)

# Create a cursor object
cursor = conn.cursor()

# Define the document types and user types
document_types = ['TI', 'CC']
user_types = [1, 2, 3, 4, 5]

# Define the number of users for each user type
user_counts = {1: 1, 2: 1, 3: 10, 4: 50, 5: 500 - 1 - 1 - 10 - 50}

# Generate and insert data
id = 7501
for user_type in user_types:
    for _ in range(user_counts[user_type]):
        cursor.execute('''
            INSERT INTO usuarios (id, tipo_documento, numero_documento, nombre, correo, tipos_usuario_id)
            VALUES (:1, :2, :3, :4, :5, :6)
        ''', (id, random.choice(document_types), fake.random_number(digits=10, fix_len=True), fake.name(), fake.email(), user_type))
        id += 1

# Commit the changes and close the connection
conn.commit()
conn.close()
