(* C:\Users\Samsung\Desktop\Pr2.ml *)

type formula=
	|True
	|False
	|Not of formula
	|AndAlso of formula * formula
	|OrElse of formula * formula
	|Imply of formula * formula
	|Equal of exp*exp
	and exp=
	|Num of int
	|Plus of exp*exp
	|Minus of exp*exp;;

let rec exxp: exp->int = fun e->
	match e with
	Num n -> n
	|Plus (e1,e2)->(exxp e1)+(exxp e2)
	|Minus (e1,e2)->(exxp e1)-(exxp e2);;

let rec eval: formula -> bool = fun e->
	match e with
	True->true
	|False->false
	|Not e ->if ((eval e)=true) then false else true
	|AndAlso (e1,e2)->(eval e1)&&(eval e2)
	|OrElse (e1,e2)-> (eval e1)||(eval e2)
	|Imply (e1,e2)-> if(((eval e1)=true)&&((eval e2)=false)) then false else true
	|Equal (e1,e2)->if((exxp e1)=(exxp e2)) then true else false;;