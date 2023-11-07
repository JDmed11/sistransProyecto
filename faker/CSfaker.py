import random
from datetime import datetime, timedelta
from faker import Faker

fake = Faker()

estado_options = ['reservado', 'consumido']

def random_date(start, end):
    return start + timedelta(
        seconds=random.randint(0, int((end - start).total_seconds())))

start_date = datetime.strptime('2020-01-01', '%Y-%m-%d')
end_date = datetime.strptime('2023-12-31', '%Y-%m-%d')

with open('consumos_servicios.sql', 'w') as f:
    for id in range(1, 3501):
        estado = random.choice(estado_options)
        reservas_alojamiento_id = random.randint(1, 7500)
        servicios_id = random.randint(1, 11)
        emisor = random.randint(14, 7500)
        fecha_fin = random_date(start_date, end_date)
        fecha_inicio_end = max(start_date, fecha_fin - timedelta(days=30))
        fecha_inicio = random_date(start_date, fecha_inicio_end)
        costo = random.randint(0, 300000)

        f.write(f"INSERT INTO consumos_servicios(id, estado, fecha_inicio, fecha_fin, costo, reservas_alojamiento_id, servicios_id, emisor) VALUES ({id}, '{estado}', '{fecha_inicio.strftime('%d/%m/%y')}', '{fecha_fin.strftime('%d/%m/%y')}', {costo}, {reservas_alojamiento_id}, {servicios_id}, {emisor});\n")
        f.write(f"UPDATE reservas_alojamiento SET saldo = saldo + {costo} WHERE id = {reservas_alojamiento_id};\n")