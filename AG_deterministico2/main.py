import random

pop = []
adap = []

pop_sel = []
f1 = []
f2 = []
#pop_mut = []
#pop_inv = []


for i in range(0,5):
	arr = []
	for j in range(0,10):
		arr.append(1)
		adap.append(1)
	pop.append(arr)

for i in range(0,2):
	arr = []
	arr2 = []
	arr3 = []
	arr4 = []
	for j in range(0,10):
		arr.append(1)
		arr2.append(1)
		arr3.append(1)
		arr4.append(1)
	pop_sel.append(arr)
	f1.append(arr2)
	f2.append(arr3)
	#pop_mut.append(arr3)
	#pop_inv.append(arr4)

def gera_pop():
	for i in range(0,5):
		for j in range(0,10):
			pop[i][j] = random.randint(0, 1)

def imprime_pop():
	for i in range(0,5):
		print("cromossomo " + str(i) + " ", end="")
		for j in range(0,10):
			print(str(pop[i][j]), end="")
		print("\n", end="")

def adaptacao():
	for i in range(0, 5):
		for j in range(0,9):
			if pop[i][j] == 0 and pop[i][j+1] == 1:
				adap[i] = adap[i + 1]
def adp(c1):
	ad = 0
	for j in range(0, 9):
		if c1[j] == 0 and c1[j + 1] == 1:
			ad = (ad+1)
	return ad

def imprime_pop_adap():
	for i in range(0,5):
		print("cromossomo" + str(i) + " ", end="")
		for j in range(0,10):
			print(str(pop[i][j]), end="")
		print(" adaptaçao = " + str(adap[i]) + "\n", end="")

def seleciona():
	for i in range(0,4):
		for j in range(i+1,5):
			if adap[i] < adap[j]:
				cromossomo = pop[i]
				pop[i] = pop[j]
				pop[j] = cromossomo
				aux = adap[i]
				adap[i] = adap[j]
				adap[j] = aux
	for i in range(0,2):
		for j in range(0,10):
			pop_sel[i][j] = pop[i][j]
def substituicao(f1,f2):
	if adp(f1) > adap[0]:
		for j in range(0, 10):
			pop[0][j] = f1[j]
			adap[0] = adp(f1)

	if adp(f2) > adap[1]:
		for j in range(0, 10):
			pop[1][j] = f2[j]
			adap[1] = adp(f2)


def cruzamento():
	for i in range(0, 1):
		for j in range(i+1, 2):
			r=random.randint(0,9)+1
			for a in range(1,5):
				for b in range(1,r):
					f1[b] = pop_sel[i][b]
					f2[b] = pop_sel[j][b]
				for b in range(r+1, 10):
					f1[b] = pop_sel[j][b]
					f2[b] = pop_sel[i][b]
				a = a+1
	substituicao(f1, f2)

def mutacao():

		for j in range(0,10):
			r = random.randint(1,10)
			if (r > 5):
				if pop_sel[i][j] == 1:
					f1[j] = 0
				else:
					f1[j] = 1
			else:
				f1[j] = pop_sel[i][j]
		for j in range(0,10):
			r = random.randint(1,10)
			if (r > 5):
				if pop_sel[i][j] == 1:
					f2[j] = 0
				else:
					f2[j] = 1
			else:
				f2[j] = pop_sel[i][j]
		substituicao(f1, f2)

def inversao():
	r1 = random.randint(1,10)
	r2 = random.randint(1,10)
	if r1 > r2:
		aux = r1
		r1 = r2
		r2 = aux
	for j in range(0, r1):
		f1[j] = pop_sel[0][j]
	for j in range(r1+1,r2):
		f1[j] = pop_sel[0][r2-(j-(r1+1))]
	for j in range(r2+1,10):
		f1[j] = pop_sel[0][j]
		r1 = random.randint(1, 10)
		r2 = random.randint(1, 10)
	if r1 > r2:
		aux = r1
		r1 = r2
		r2 = aux
	for j in range(0, r1):
		f2[j] = pop_sel[1][j]
		for j in range(r1 + 1, r2):
			f2[j] = pop_sel[1][r2 - (j - (r1 + 1))]
		for j in range(r2 + 1, 10):
			f2[j] =  pop_sel[1][j]
    substituicao(f1, f2)

#pop_nova = []
#adap_nova = adap
'''
for i in range(0,8):
	arr = []
	for j in range(0,10):
		arr.append(1)
	pop_nova.append(arr)

def nova_pop():
	for i in range(0,2):
		for j in range(0,10):
			pop_nova[i][j] = pop_sel[i][j]
	for i in range(2,4):
		for j in range(0,10):
			pop_nova[i][j] = pop_des[i][j]
	for i in range(4,6):
		for j in range(0,10):
			pop_nova[i][j] = pop_mut[i][j]
	for i in range(6,8):
		for j in range(0,10):
			pop_nova[i][j] = pop_inv[i][j]

	for i in range(0, 8):
		for j in range(0,9):
			if pop_nova[i][j] == 0 and pop_nova[i][j+1] == 1:
				adap_nova[i] = adap_nova[i + 1]
	for i in range(0,7):
		for j in range(i+1,8):
			if adap_nova[i] < adap_nova[j]:
				cromossomo = pop_nova[i]
				pop_nova[i] = pop_nova[j]
				pop_nova[j] = cromossomo
				aux = adap_nova[i]
				adap_nova[i] = adap_nova[j]
				adap_nova[j] = aux
	for i in range(0,5):
		for j in range(0,10):
			pop[i][j] = pop_nova[i][j]'''
gera_pop()
imprime_pop()
adaptacao()


maior = 0
def calc_maior():
	maior = 0
	for i in adap:
		if maior < i:
			maior = i

calc_maior()
while maior != 5:
	seleciona()
	cruzamento()
	mutacao()
	inversao()
	#nova_pop()
	calc_maior()

imprime_pop()