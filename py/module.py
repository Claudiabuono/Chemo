import random
import json

# Il modulo prende in input i pazienti sottomessi dal medico: per far comunicare Java e Python
# utilizziamo un file condiviso tra i due linguaggi: Java scrive l'input, preso da Python, e Python
# scrive l'output preso da Java per visualizzare lo schedule
file = open('patients.json', 'r')
patients = json.loads(file.read())

file2 = open('medicines.json', 'r')
meds = json.loads(file2.read())

prova = [1, 2, 3, 4, 5]


def encodeIndividual(patientList, numSeats, numHours, numDays):
    column = len(patientList)
    row = numSeats * numHours * numDays
    matrix = []

    if column < row or column == row:
        for i in range(column):
            for j in range(row):
                lista = random.sample([0, 1], counts=[column - 1, 1], k=column)

            matrix.append(lista)
        return matrix
    elif column > row:  # o si impone un limite al form o si eliminano pazienti a random
        print("Selezionare un numero di pazienti ridotto: inferiore alle X unita")


def countZeros(lista):
    count = 0
    for elem in lista:
        if elem == 0:
            count += 1
    return count


def countOne(lista):
    count = 0
    for elem in lista:
        if elem == 1:
            count += 1
    return count


def generation(patients, numSeats, numHours, numDays):
    population_size = random.randrange(len(patients) + 1, 50)
    print("Size population: " + str(population_size))
    population = []
    newIndividual = []
    for i in range(population_size):
        newIndividual = encodeIndividual(patients, numSeats, numHours, numDays)
        population.append(newIndividual)
        newIndividual = []
    return population


def fitness(generation, patients):
    values = []
    conflict = 0
    for indi in generation:
        fit_of_indi = 0
        lista_indici_pazienti = []
        for elem in indi:
            index = elem.index(1)
            lista_indici_pazienti.append(index)

        for i in range(len(lista_indici_pazienti)):
            for j in range(1):
                fit = 0
                if j == i:
                    continue
                elif patients[lista_indici_pazienti[i]]['medicineId'] == patients[lista_indici_pazienti[j]]['medicineId']:
                    fit += 0.7
                else:
                    fit += 0.1
            fit_of_indi += fit

        #valutazione fatta in base a quanto consumo si fa di un faramco:

        values.append(fit_of_indi)
    return values


generation_list = generation(patients, 10, 6, 5)
fit = fitness(generation_list, patients)
print(fit)
