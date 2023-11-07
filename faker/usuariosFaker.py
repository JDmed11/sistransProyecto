import random
from faker import Faker

fake = Faker()

tipo_documento_options = ['TI', 'CC']
email_domains = ['gmail.com', 'hotmail.com']
user_counts = {1: 1, 2: 1, 3: 10, 4: 100, 5: 7500 - 1 - 1 - 10 - 100}
document_numbers = set()

with open('usuarios.sql', 'w') as f:
    id = 1
    for user_type, count in user_counts.items():
        for _ in range(count):
            tipo_documento = random.choice(tipo_documento_options)
            numero_documento = fake.unique.random_number(digits=10, fix_len=True)
            document_numbers.add(numero_documento)
            nombre = fake.name().replace("'", "''")
            correo = f"{nombre.split()[0].lower()}@{random.choice(email_domains)}"
            f.write(f"INSERT INTO usuarios (id, tipo_documento, numero_documento, nombre, correo, tipos_usuario_id) VALUES ({id}, '{tipo_documento}', '{numero_documento}', '{nombre}', '{correo}', {user_type});\n")
            id += 1