#include<stdio.h>
int cont_call = 0; // tot bytes de n*(fat(n-1) (double)
int cont_qtd_call = 0; // qtd de chamadas da funcao
//padrao
int cont_int = 0;
int cont_0 = 0;
double fat (int n) {
    if (n == 0) {
        cont_int += sizeof(n); //n eh inteiro (4 bytes)
        cont_0++; //ele entra aqui 100 vezes  4 x 100 = 400 bytes aqui
        return 1;
    } else {
        cont_call+= sizeof(n*fat(n-1)); //aqui ele vai retornar o valor de double (8 bytes)
        cont_qtd_call++; //essa funcao eh chamada 4950 vezes
        return n * fat(n-1); // vai ser retornada 4950 x 8  bytes = 39600 bytes
    }
    //Ou seja nessa chamada o codigo vai gastar no pior caso para fazer as 100 chamadas 40000 bytes (39600+400) na pilha
}

double vec[100]; // aqui nosso vetor ocupa 8  X 100 = 800 bytes


int main (void) {
    int tam = 0; //800 bytes
    printf("Bytes vec .....................................:%d\n",sizeof(vec));
    for (int i=0; i<100; i++) {
        vec[i] = fat(i);
        tam += sizeof(vec[i]);
    }
    printf("vec2 no final do for ->........................:%d\n",tam);
    printf("bytes do retorno de (n * fat(n-1) (double) -> .:%d\n",cont_call);
    printf("Qtd que entra em else -> ......................:%d\n",cont_qtd_call);
    printf("tamanho de n para o caso default -> ...........:%d\n",cont_int);
    printf("Qtd de vezes que entra em default -> ..........:%d\n",cont_0);
    printf("O programa consome ao todo ....................:%d\n",cont_call+cont_int+tam);

return 0;
}
