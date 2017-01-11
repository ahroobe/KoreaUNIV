#include<stdio.h>
#include<conio.h>
#include<Windows.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>
int now_x,now_y,ptr,ptr2;                       //현재 위치를 표시하는 정수이다.그리고 1P와 2P의 정보를 담는 변수이다.
int count;                     //지금이 흑돌차례인지 백돌차례인지 나타낸다.
void gotoxy(int x,int y);        //좌표로 이동하게 하는 함수
void move();                    //방향키에 따라 움직이고 esc키가 눌려지면 돌을 놓는 작업을 하게 하는 함수이다.
void drawing();                 //돌을 놓는 함수이다.
int check[100][100];            //바둑돌이 잇는지 없는지 표시하기 위해 2차원배열을 선언했다.
int win_or_lose();              //누가 이겼는지 판단하는 함수이다.
int N;                       //이때까지 등록된 사용자 수이다.
void prevent();                   //바둑돌을 밖에 못두도록 한다.
FILE *in = fopen("user_inform.txt","r"); //user_inform을 읽는다.
struct user
{
	char name[7];
	int age,win,lose;
}inform[100];//이름과 나이, 총 승패를 기록해놓을 수 있는 구조체를 선언했다.

int main()
{
	int i;
	int user;
	int t;
	char str[100];

	now_x=15;                     //현재 위치를 중앙에 오게끔 선언했다.
	now_y=6;

	/*
	user_inform에 저장되있던 사용자 수 N을 입력 받고, 그 입력 받은 것으로 저장되있던 사용자들을 입력받는다.
	그 후 출력한다. */
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
	//오목 게임을 진행할 2사람의 정보를 새로 입력하거나 불러오게끔 한다.
	for(t=1;t<=2;t++){

back:

		printf("\n\n\n\t\tfor new user press 1");//새로운 사람
		printf("\n\t\tfor used user press 2\n");//불러오기
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
				if(strcmp(inform[i].name,str)==0)//입력받은 이름이 같으면 for문을 멈추어 i의 증가를 막는다.
				{
					break;
				}
			}
			if(i==N)//만약 i가 계속 증가해서 N에 도달했다면 일치하는 것이 없다는 것을 의미하므로 없음을 말한 뒤 다시 돌아가게 한다.
			{
				printf("There is no %s",str);
				Sleep(1000);
				system("cls");
				goto back;//이미 작성하고 난 뒤 넣어서 고치기 어려워서 goto문을 썼습니다.
			}
			if(t==1)//저장된 i를 사용자 정보에 넣는다. 1일 경우 1P인 ptr, 2일 경우 2P인 ptr2 
				ptr=i;
			else
				ptr2=i;


			system("cls");

		}
	}
	system("cls");
	printf("\n\n\n\t\t\tgame rule\n");
	printf("you can move with joystick '↑←→↓' and put stone with 'ESC' key\n");//전반적인 게임 규칙 설명
	Sleep(3000);
	system("cls");

	//바둑판 그리기
	printf("┌┬┬┬┬┬┬┬┬┬┬┬┬┬┐\n");
	for(i=0;i<13;i++)
		printf("├┼┼┼┼┼┼┼┼┼┼┼┼┼┤\n");
	printf("└┴┴┴┴┴┴┴┴┴┴┴┴┴┘\n");
	//바둑판이 없는 곳에 제일 처음 흰색돌의 차례라고 얘기한다.
	gotoxy(1,16);
	printf("white turn!");
	//다시 중앙에 와서 move()를 해 게임을 플레이 하도록 한다.
	gotoxy(now_x,now_y);
	move();
	gotoxy(1,16);
	printf("                       ");
	gotoxy(1,16);
	if(count%2==1){
		printf("white wins!\n");
		inform[ptr].win++;//백돌은 1P이므로 1P의 승을 1올리고 2P의 패를 1 올린다. 아래는 반대.
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
	//게임이 끝난 뒤 사용자들의 전적을 나타낸다.
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
void gotoxy(int x,int y)//좌표 이동 함수
{
	COORD Cur;
	Cur.X=x;
	Cur.Y=y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),Cur);
}
void move()//전반적인 조작하는 함수
{
	int i;
	char ch;
	do

	{

		ch = getch();
		gotoxy(now_x,now_y);
		//하나를 입력받아 그게 방향키면 이동하도록, esc키면 바둑돌을 그리도록 한다.
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
		//이겼는지 졌는지를 따져준다. 이겼으면 do while문을 벗어난다. 그러고 move()함수가 끝난다.
		i=win_or_lose();
		prevent();

	}while(i!=1);

}
void drawing()//바둑돌을 그리는 함수
{
	//이미 1이나 2로 되었다면(돌이 있다면) 0.3초간 여기 둘수 없다는 것을 나타내고 못 두게 한다
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
		//짝수번째에는 백돌,이런식으로 돌을 둘때마다 count를 올려주며 백돌,흑돌을 차례대로 놓을수 있게 한다.
		if(count%2==0)
		{
			printf("\b●");
			check[now_x][now_y]=1;
			gotoxy(1,16);
			printf("black turn!\n");
			gotoxy(now_x,now_y);
		}
		if(count%2==1)
		{
			printf("\b○");
			check[now_x][now_y]=2;
			gotoxy(1,16);
			printf("white turn!\n");
			gotoxy(now_x,now_y);
		}
		count++;
	}

}


int win_or_lose()//이김을 판단하는 함수
{

	int times=0;


	//가로로 맞는게 있는지 판단한다.
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

		//세로로 맞는게 있는지 판단한다.
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
		//대각선으로 맞는게 있는지 판단한다.
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

void prevent()//밖으로 나가는걸 제어하는 함수
{
	//현재 좌표가 벗어났을 경우 나갈수없다고 0.3초간 프린트 하고 정중앙으로 돌아오게 한다.
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
