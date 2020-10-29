/*
 * CselfRef.c
 *
 *  Created on: Apr 2, 2017
 *      Author: Tianyi Ma
 */

#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
typedef int BOOLEAN;

struct Node {
	int value;
	struct Node *next;
};


void insert(int x, struct Node **pL){
	printf("inserting");
	//printf(NULL);
	//printf(*pL);
	//printf(!(*pL));
	printf("\n");
	if (*pL){
		printf("inserting=======");
		//struct Node *ptr = (struct Node*)malloc(sizeof(struct Node));
		//ptr = malloc(sizeof(struct Node));
		*pL = malloc(1*sizeof(struct Node));
		(*pL)->value = x;
		(*pL)->next = NULL;
	} else if (!(*pL)){
		printf("%d", (*pL)->value);
		printf("inserting.......");
		printf("\n");
		insert(x,&((*pL)->next));
	}
}

void printList(struct Node * L){
	printf("printing");
		printf("\n");
	if(L != NULL){
		printf("%d", L->value);
		printf(" ");
		printList(L->next);
	}
}

BOOLEAN lookup(int x, struct Node * L){
	printf("looking up");
		printf("\n");
	//BOOLEAN check=FALSE;
	if(L != NULL){
		if (L->value != x){
			lookup(x, (L->next));
			return FALSE;
		} else {
			return TRUE;
			//check=TRUE;
		}
	} else {
		return FALSE;
	}
	//return check;
}

void delete(int x, struct Node **pL){
	if(!(*pL)){
		if ((*pL)->value != x){
			delete(x, &((*pL)->next));
		} else {
			(*pL) = (*pL)->next;
		}
	}
}

int main(){
	printf("beginning");
		printf("\n");
	struct Node *L;
	for (int i = 1; i < 20; i += 2){
		printf("for loop");
		printf("\n");
		insert(i,&L);
	}
	printList(L);
	printf("\n");
	for (int i = 0; i < 20; i++){
		printf("%d %s FOUND\n",i,((lookup(i,L) == TRUE) ? "": "NOT"));
	}
	printf("\n");
	for (int i = 0; i < 20; i += 3){
		delete(i,&L);
	}
}

