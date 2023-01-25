import json
import random as ran
p1 = {
    "name" : "Roberto",
    "surname" : "Rossi",
    "age": 22
}

p2 = {
    "name" : "Camilla",
    "surname" : "Leo",
    "age": 12
}

p3 = {
    "name" : "Rodolfo",
    "surname" : "Ponticelli",
    "age": 44
}

p4 = {
    "name" : "Lucia",
    "surname" : "Caiazza",
    "age": 99
}

lista = [p1, p2, p3, p4]
def encodeIndividual(patientList):
    column = len(patientList)
    row = 50 #num poltrone * num orari * 7 gg
    matrix = []
    #Occorre inserire un controllo: se il numero di pazienti è inferiore al numero degli appuntamenti cosa facciamo?

    if column < row or column == row:
        for i in range(column):
            for j in range(row):
                lista = ran.sample([0, 1], counts=[column - 1, 1], k=column)

            #la matrice risultante è di taglia column * column
            matrix.append(lista)

        for k in zip(*matrix):
            print(k)
    elif column > row: #o si impone un limite al form o si eliminano pazienti a random
        print("Selezionare un numero di pazienti ridotto: inferiore alle X unita")


encodeIndividual(lista)

