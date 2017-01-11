#include<stdio.h>
#include<conio.h>
#include<Windows.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>
int now_x,now_y,ptr,ptr2;                       //���� ��ġ�� ǥ���ϴ� �����̴�.�׸��� 1P�� 2P�� ������ ��� �����̴�.
int count;                     //������ �浹�������� �鵹�������� ��Ÿ����.
void gotoxy(int x,int y);        //��ǥ�� �̵��ϰ� �ϴ� �Լ�
void move();                    //����Ű�� ���� �����̰� escŰ�� �������� ���� ���� �۾��� �ϰ� �ϴ� �Լ��̴�.
void drawing();                 //���� ���� �Լ��̴�.
int check[100][100];            //�ٵϵ��� �մ��� ������ ǥ���ϱ� ���� 2�����迭�� �����ߴ�.
int win_or_lose();              //���� �̰���� �Ǵ��ϴ� �Լ��̴�.
int N;                       //�̶����� ��ϵ� ����� ���̴�.
void prevent();                   //�ٵϵ��� �ۿ� ���ε��� �Ѵ�.
FILE *in = fopen("user_inform.txt","r"); //user_inform�� �д´�.
struct user
{
	char name[7];
	int age,win,lose;
}inform[100];//�̸��� ����, �� ���и� ����س��� �� �ִ� ����ü�� �����ߴ�.

int main()
{
	int i;
	int user;
	int t;
	char str[100];

	now_x=15;                     //���� ��ġ�� �߾ӿ� ���Բ� �����ߴ�.
	now_y=6;

	/*
	user_inform�� ������ִ� ����� �� N�� �Է� �ް�, �� �Է� ���� ������ ������ִ� ����ڵ��� �Է¹޴´�.
	�� �� ����Ѵ�. */
	fscanf(in,"%d",&N); 
	for(i=0;i<N;i++){
		fscanf(in,"%s %d %d %d\n",inform[i].name,&inform[i].age,&inform[i].win,&inform[i].lose);
	}
	printf("%d people participated\n",N);
	for(i=0;i<N;i++){
		printf("%s %d %d %d\n",inform[i].name,inform[i].age,inform[i].win,inform[i].lose);
	}

	Sleep(3000);
	system("cls");
	//���� ������ ������ 2����� ������ ���� �Է��ϰų� �ҷ����Բ� �Ѵ�.
	for(t=1;t<=2;t++){

back:

		printf("\n\n\n\t\tfor new user press 1");//���ο� ���
		printf("\n\t\tfor used user press 2\n");//�ҷ�����
		scanf("%d",&user);
		if(user==1){
			printf("input user %d's name",t);
			scanf("%s",inform[N].name);
			printf("input user %d's age",t);
			scanf("%d",&inform[N].age);
			fflush(stdin);
			N++;
			if(t==1)
				ptr=N-1;
			else
				ptr2=N-1;
			system("cls");
		}
		if(user==2)
		{

			printf("input user %d's name\n",t);
			scanf("%s",str);
			fflush(stdin);
			for(i=0;i<N;i++)
			{
				if(strcmp(inform[i].name,str)==0)//�Է¹��� �̸��� ������ for���� ���߾� i�� ������ ���´�.
				{
					break;
				}
			}
			if(i==N)//���� i�� ��� �����ؼ� N�� �����ߴٸ� ��ġ�ϴ� ���� ���ٴ� ���� �ǹ��ϹǷ� ������ ���� �� �ٽ� ���ư��� �Ѵ�.
			{
				printf("There is no %s",str);
				Sleep(1000);
				system("cls");
				goto back;//�̹� �ۼ��ϰ� �� �� �־ ��ġ�� ������� goto���� ����ϴ�.
			}
			if(t==1)//����� i�� ����� ������ �ִ´�. 1�� ��� 1P�� ptr, 2�� ��� 2P�� ptr2 
				ptr=i;
			else
				ptr2=i;


			system("cls");

		}
	}
	system("cls");
	printf("\n\n\n\t\t\tgame rule\n");
	printf("you can move with joystick '�����' and put stone with 'ESC' key\n");//�������� ���� ��Ģ ����
	Sleep(3000);
	system("cls");

	//�ٵ��� �׸���
	printf("������������������������������\n");
	for(i=0;i<13;i++)
		printf("������������������������������\n");
	printf("������������������������������\n");
	//�ٵ����� ���� ���� ���� ó�� ������� ���ʶ�� ����Ѵ�.
	gotoxy(1,16);
	printf("white turn!");
	//�ٽ� �߾ӿ� �ͼ� move()�� �� ������ �÷��� �ϵ��� �Ѵ�.
	gotoxy(now_x,now_y);
	move();
	gotoxy(1,16);
	printf("                       ");
	gotoxy(1,16);
	if(count%2==1){
		printf("white wins!\n");
		inform[ptr].win++;//�鵹�� 1P�̹Ƿ� 1P�� ���� 1�ø��� 2P�� �и� 1 �ø���. �Ʒ��� �ݴ�.
		inform[ptr2].lose++;
	}
	else if(count%2==0){
		printf("black wins!\n");
		inform[ptr2].win++;
		inform[ptr].lose++;
	}
	gotoxy(1,18);
	Sleep(2000);
	system("cls");
	//������ ���� �� ����ڵ��� ������ ��Ÿ����.
	printf("after game, user's information\n"); 
	for(i=0;i<N;i++)
	{
		printf("%s %d %d %d\n",inform[i].name,inform[i].age,inform[i].win,inform[i].lose);
	}
	FILE *out = fopen("user_inform.txt","w");
	fprintf(out,"%d\n",N);
	for(i=0;i<N;i++)
	{
		fprintf(out,"%s %d %d %d\n",inform[i].name,inform[i].age,inform[i].win,inform[i].lose);
	}
	
	fclose(in);
	system("pause");
	return 0;

}
void gotoxy(int x,int y)//��ǥ �̵� �Լ�
{
	COORD Cur;
	Cur.X=x;
	Cur.Y=y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),Cur);
}
void move()//�������� �����ϴ� �Լ�
{
	int i;
	char ch;
	do

	{

		ch = getch();
		gotoxy(now_x,now_y);
		//�ϳ��� �Է¹޾� �װ� ����Ű�� �̵��ϵ���, escŰ�� �ٵϵ��� �׸����� �Ѵ�.
		if(ch==72)
			now_y=now_y-1;
		else if(ch==80)
			now_y=now_y+1;
		else if(ch==75)
			now_x=now_x-2;
		else if(ch==77)
			now_x=now_x+2;
		else if(ch==0x1B)
			drawing();
		gotoxy(now_x,now_y);
		//�̰���� �������� �����ش�. �̰����� do while���� �����. �׷��� move()�Լ��� ������.
		i=win_or_lose();
		prevent();

	}while(i!=1);

}
void drawing()//�ٵϵ��� �׸��� �Լ�
{
	//�̹� 1�̳� 2�� �Ǿ��ٸ�(���� �ִٸ�) 0.3�ʰ� ���� �Ѽ� ���ٴ� ���� ��Ÿ���� �� �ΰ� �Ѵ�
	if(check[now_x][now_y]==1 || check[now_x][now_y]==2)
	{
		gotoxy(1,17);
		printf("you cannot put it in here!");
		Sleep(300);
		gotoxy(1,17);
		printf("                          ");
		gotoxy(now_x,now_y);
	}

	else
	{
		//¦����°���� �鵹,�̷������� ���� �Ѷ����� count�� �÷��ָ� �鵹,�浹�� ���ʴ�� ������ �ְ� �Ѵ�.
		if(count%2==0)
		{
			printf("\b��");
			check[now_x][now_y]=1;
			gotoxy(1,16);
			printf("black turn!\n");
			gotoxy(now_x,now_y);
		}
		if(count%2==1)
		{
			printf("\b��");
			check[now_x][now_y]=2;
			gotoxy(1,16);
			printf("white turn!\n");
			gotoxy(now_x,now_y);
		}
		count++;
	}

}


int win_or_lose()//�̱��� �Ǵ��ϴ� �Լ�
{

	int times=0;


	//���η� �´°� �ִ��� �Ǵ��Ѵ�.
	if(check[now_x][now_y]==1 || check[now_x][now_y]==2)
	{

		if(now_x>=8)
		{
			if(check[now_x-2][now_y]==check[now_x][now_y] && check[now_x-4][now_y]==check[now_x][now_y] && check[now_x-6][now_y]==check[now_x][now_y] && check[now_x-8][now_y]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_x>=6)
		{
			if(check[now_x-2][now_y]==check[now_x][now_y] && check[now_x-4][now_y]==check[now_x][now_y] && check[now_x-6][now_y]==check[now_x][now_y] && check[now_x+2][now_y]==check[now_x][now_y])

				return 1;
		}

		if(now_x>=4)
		{
			if(check[now_x-2][now_y]==check[now_x][now_y] && check[now_x-4][now_y]==check[now_x][now_y] && check[now_x+4][now_y]==check[now_x][now_y] && check[now_x+2][now_y]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_x>=2)
		{
			if(check[now_x-2][now_y]==check[now_x][now_y] && check[now_x+6][now_y]==check[now_x][now_y] && check[now_x+4][now_y]==check[now_x][now_y] && check[now_x+2][now_y]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(check[now_x+8][now_y]==check[now_x][now_y] && check[now_x+6][now_y]==check[now_x][now_y] && check[now_x+4][now_y]==check[now_x][now_y] && check[now_x+2][now_y]==check[now_x][now_y])
		{

			return 1;
		}

		//���η� �´°� �ִ��� �Ǵ��Ѵ�.
		if(check[now_x][now_y+1]==check[now_x][now_y] && check[now_x][now_y+2]==check[now_x][now_y] && check[now_x][now_y+3]==check[now_x][now_y] && check[now_x][now_y+4]==check[now_x][now_y])
		{

			return 1;
		}
		if(now_y>=1)
		{
			if(check[now_x][now_y+1]==check[now_x][now_y] && check[now_x][now_y+2]==check[now_x][now_y] && check[now_x][now_y+3]==check[now_x][now_y] && check[now_x][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_y>=2)
		{

			if(check[now_x][now_y+1]==check[now_x][now_y] && check[now_x][now_y+2]==check[now_x][now_y] && check[now_x][now_y-2]==check[now_x][now_y] && check[now_x][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_y>=3)
		{
			if(check[now_x][now_y+1]==check[now_x][now_y] && check[now_x][now_y-3]==check[now_x][now_y] && check[now_x][now_y-2]==check[now_x][now_y] && check[now_x][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_y>=4)
		{
			if(check[now_x][now_y-4]==check[now_x][now_y] && check[now_x][now_y-3]==check[now_x][now_y] && check[now_x][now_y-2]==check[now_x][now_y] && check[now_x][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		//�밢������ �´°� �ִ��� �Ǵ��Ѵ�.
		if(check[now_x+2][now_y+1]==check[now_x][now_y] && check[now_x+4][now_y+2]==check[now_x][now_y] && check[now_x+6][now_y+3]==check[now_x][now_y] && check[now_x+8][now_y+4]==check[now_x][now_y])
		{

			return 1;
		}
		if(now_x>=2 && now_y>=1){
			if(check[now_x+2][now_y+1]==check[now_x][now_y] && check[now_x+4][now_y+2]==check[now_x][now_y] && check[now_x+6][now_y+3]==check[now_x][now_y] && check[now_x-2][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_x>=4 && now_y>=2)
		{
			if(check[now_x+2][now_y+1]==check[now_x][now_y] && check[now_x+4][now_y+2]==check[now_x][now_y] && check[now_x-4][now_y-2]==check[now_x][now_y] && check[now_x-2][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_x>=6 && now_y>=3){
			if(check[now_x+2][now_y+1]==check[now_x][now_y] && check[now_x-6][now_y-3]==check[now_x][now_y] && check[now_x-4][now_y-2]==check[now_x][now_y] && check[now_x-2][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_x>=8 && now_y>=4)
		{
			if(check[now_x-8][now_y-4]==check[now_x][now_y] && check[now_x-6][now_y-3]==check[now_x][now_y] && check[now_x-4][now_y-2]==check[now_x][now_y] && check[now_x-2][now_y-1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		if(now_y>=4)
		{	
			if(check[now_x+2][now_y-1]==check[now_x][now_y] && check[now_x+4][now_y-2]==check[now_x][now_y] && check[now_x+6][now_y-3]==check[now_x][now_y] && check[now_x+8][now_y-4]==check[now_x][now_y])

			{

				return 1;
			}
		}
		if(now_x>=2 && now_y>=3)
		{		
			if(check[now_x+2][now_y-1]==check[now_x][now_y] && check[now_x+4][now_y-2]==check[now_x][now_y] && check[now_x+6][now_y-3]==check[now_x][now_y] && check[now_x-2][now_y+1]==check[now_x][now_y])
			{

				return 1;
			}
		}		
		if(now_x>=4 && now_y>=2)
		{			
			if(check[now_x+2][now_y-1]==check[now_x][now_y] && check[now_x+4][now_y-2]==check[now_x][now_y] && check[now_x-4][now_y+2]==check[now_x][now_y] && check[now_x-2][now_y+1]==check[now_x][now_y])
			{

				return 1;
			}
		}			
		if(now_x>=6 && now_y>=1)
		{				
			if(check[now_x+2][now_y-1]==check[now_x][now_y] && check[now_x-6][now_y+3]==check[now_x][now_y] && check[now_x-4][now_y+2]==check[now_x][now_y] && check[now_x-2][now_y+1]==check[now_x][now_y])
			{

				return 1;
			}
		}				
		if(now_x>=8)
		{					
			if(check[now_x-8][now_y+4]==check[now_x][now_y] && check[now_x-6][now_y+3]==check[now_x][now_y] && check[now_x-4][now_y+2]==check[now_x][now_y] && check[now_x-2][now_y+1]==check[now_x][now_y])
			{

				return 1;
			}
		}
		else
			return 0;
	}
	return 0;
}

void prevent()//������ �����°� �����ϴ� �Լ�
{
	//���� ��ǥ�� ����� ��� ���������ٰ� 0.3�ʰ� ����Ʈ �ϰ� ���߾����� ���ƿ��� �Ѵ�.
	if(now_x>29 || now_y>14 || now_x<0 || now_y<0)
	{
		gotoxy(31,2);
		printf("you cannot go out of place!");
		Sleep(300);
		gotoxy(31,2);
		printf("                            ");
		now_x=15;
		now_y=6;
		gotoxy(now_x,now_y);
	}
}
