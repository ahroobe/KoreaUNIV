
let hd a=
	match a with
	[]->raise(Failure "hd:empty list")
	|hd::tl->hd;;

let tl a
	=match a with
	[]->raise(Failure "tl:empty list")
	|hd::tl->tl;;

let rec length a=
	match a with
	[]->0
	|hd::tl->1+(length tl);;

let rec fn a=
	match a with
	[]->raise(Failure "empty!")
	|hd::tl->if(tl=[]) then hd else fn(tl);;

let goaway a=
 	match a with
 	[]->[]
 	|hd::tl-> tl @ [hd];;

type aexp=
	|Const of int
	|Var of string
	|Power of string * int
	|Times of aexp list
	|Sum of aexp list;;

let rec diff2:aexp*string*aexp->aexp = fun (e,x,k)->
	match (e,x,k) with
	(e,"",k)->e
	|(Times e,x,Const 0)->diff2(Times e,x,fn e)
	|(Const e,x,k)->(Const 0)
	|(Var e,x,k)->if(e=x) then (Const 1) else (Const 0)
	|(Sum e,x,k)->if(tl e=[]) then (diff2 (hd  e, x,k)) else Sum ([(diff2 (hd  e, x,k))] @ [diff2((Sum(tl e)), x,k)])
	|(Sum [],x,k)->Var "";
	|(Power (e1,e2),x,k)-> if (e1=x) then (Times( [(Const e2)] @ [ ( Power (e1,(e2-1))) ])) else if (e2=1) then (Var e1) else  (Const 0) 
	|(Times e,x,k)->if(length e = 2) then Sum ([Times( [diff2( (hd e) ,x,k)] @ tl e )] @ [Times([diff2((fn e),x,k)] @ [hd e]) ] ) 
	else if ((hd e)=k) then Times([diff2 (hd e,x,k)]@ tl e) 
	else Sum ( [Times( [diff2(hd e,x,k)] @ (tl e))] @ [diff2(Times(goaway(e)),x,k)])
	|(Times [],x,k)->Var "";;

let diff (a,b)= diff2(a,b,Const 0);;

diff(Sum [Power("x",2);Times[Const 2; Var "x"];Const 1],"x");;
diff(Times[(Power("x",2)); Power("x",3) ],"x");;
diff(Times[(Power("x",2)); Power("x",3); Power("x",4) ],"x");;
