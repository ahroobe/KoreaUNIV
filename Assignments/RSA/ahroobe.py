import math
import random

def fib(n):
	a,b = 0,1
	while a < n:
		print(a, end=' ')
		a,b= b,a+b
	print()

#faster isprime rabinMiller method. (from internet)
def rabinMiller(num):

    s = num - 1
    t = 0
    while s % 2 == 0:
        s = s // 2
        t += 1
    for trials in range(5):
        a = random.randrange(2, num - 1)
        v = pow(a, s, num)
        if v != 1: 
            i = 0
            while v != (num - 1):
                if i == t - 1:
                    return False
                else:
                    i = i + 1
                    v = (v ** 2) % num
    return True

#Is this prime? or not?
def isprime(n):
    if (n&1 != 0):
          return rabinMiller(n)
    return False

#generate Prime number with bit k.
def genprime(k):
     r=100*(math.log(k,2)+1)
     while r>0:
         n = random.randrange(2**(k-1),2**(k))
         r-=1
         if isprime(n) == True:
             return n
     return "Failure after"

#gcd!
def gcd(a,b):
	if b==0:
		return a
	else:
		return gcd(b,a%b)

#generate e! disjoint with pi
def gene(n,pi,k):
	for i in range(k,n-1):
		if gcd(pi,i)==1:
			return i
	return -1

#genrate d!
def gend(e,pi):
	for i in range(0,e-1):
		if (((pi*i)+1)%e==0):
			return round((i*pi+1)/e)
	return -1

#Generate RSA. n,e,d with bit k.
def genrsa(k):
	good=True
	a=1
	p=genprime(k)
	q=genprime(k)
	n = p*q ## n
	pi=(p-1)*(q-1)#oyler pi
	while(good):#for the case that there is no d.
		e = gene(n,pi,a)
		d = gend(e,pi)
		if d==-1:
			a=a+1
		else:
			good=False
	return(n,e,d)

#encryption
def rsaenc(n,e,m):
	return m**e%n
#decryption
def rsadec(n,d,c):
	return c**d%n
