from faker import Faker
import random

# Create a Faker instance
fake = Faker()

# Define the document types and user types
document_types = ['TI', 'CC']
user_types = [1, 2, 3, 4, 5]

# Define the number of users for each user type
user_counts = {1: 1, 2: 1, 3: 10, 4: 50, 5: 7500 - 1 - 1 - 10 - 50}

# Open a text file for writing
with open('insertions.sql', 'w') as f:
    # Generate and write SQL insert statements
    id = 1
    for user_type in user_types:
        for _ in range(user_counts[user_type]):
            f.write(f'''
                INSERT INTO usuarios (id, tipo_documento, numero_documento, nombre, correo, tipos_usuario_id)
                VALUES ({id}, '{random.choice(document_types)}', '{fake.random_number(digits=10, fix_len=True)}', '{fake.name().replace("'", "''")}', '{fake.email()}', {user_type});
            ''')
            id += 1


