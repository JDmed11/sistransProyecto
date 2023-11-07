import random
from datetime import datetime, timedelta
from faker import Faker

def random_date(start, end):
    return start + timedelta(
        seconds=random.randint(0, int((end - start).total_seconds())))

def habitacion_based_on_acompaniantes(acompaniantes):
    if 1 <= acompaniantes <= 3:
        return random.randint(1, 19)
    elif 4 <= acompaniantes <= 6:
        return random.randint(20, 39)
    else:
        return random.randint(40, 50)

start_date = datetime.strptime('2020-01-01', '%Y-%m-%d')
end_date = datetime.strptime('2023-12-31', '%Y-%m-%d')

with open('reservas_alojamiento.sql', 'w') as f:
    for id in range(1, 7501):
        fecha_entrada = random_date(start_date, end_date)
        fecha_salida = random_date(fecha_entrada, min(end_date, fecha_entrada + timedelta(days=30)))
        acompaniantes = random.randint(1, 10)
        habitacion = habitacion_based_on_acompaniantes(acompaniantes)
        plan_consumo = random.randint(1, 4)
        cliente = random.randint(10, 7500)
        saldo = 0

        f.write(f"INSERT INTO reservas_alojamientos (id, fecha_entrada, fecha_salida, acompaniantes, estado, habitacion, plan_consumo, cliente, saldo) VALUES ({id}, '{fecha_entrada.strftime('%d/%m/%y')}', '{fecha_salida.strftime('%d/%m/%y')}', {acompaniantes}, 'reservada', {habitacion}, {plan_consumo}, {cliente}, {saldo});\n")