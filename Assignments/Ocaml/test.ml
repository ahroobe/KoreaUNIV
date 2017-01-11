
type cmd = 
	| Skip
	| Assign of string*exp
	| Seq of cmd*cmd
	| If of exp*cmd*cmd
	| While of exp*cmd
	and exp=
	| N of int
	| Var of string
	| Plus of exp*exp
	| Mult of exp*exp;;

type cal = exp->exp;;

let rec cals:exp*int->exp*int = fun (e,b)->
	match (e,b) with
	(N e,b) ->(N e,b)
	|(a,1)->(a,1)
	|(Var e,b) ->(Var e,b)

	|(Plus (Mult (N k,Var l),Mult (N m,Var n)),b)->if ( l=n) then (Mult (N(k+m),Var l),b) else (Plus (Mult (N k,Var l),Mult (N m,Var n)),b)
	|(Plus (Mult (N k,Var l),Mult (Var n,N m)),b)->if ( l=n) then (Mult (N(k+m),Var l),b) else (Plus (Mult (N k,Var l),Mult (N m,Var n)),b)
	|(Plus (Mult (Var l,N k),Mult (Var n,N m)),b)->if ( l=n) then (Mult (N(k+m),Var l),b) else (Plus (Mult (N k,Var l),Mult (N m,Var n)),b)
	|(Plus (Mult (Var l,N k),Mult (N m,Var n)),b)->if ( l=n) then (Mult (N(k+m),Var l),b) else (Plus (Mult (N k,Var l),Mult (N m,Var n)),b)
	|(Plus(N e1, Plus(N m, Var n)),b)->(Plus(N(e1+m),Var n),b)
	|(Plus(N e1, Plus(Var n, N m)),b)->(Plus(N(e1+m),Var n),b)
	|(Plus( Plus(Var n, N m),N e1),b)->(Plus(N(e1+m),Var n),b)
	|(Plus( Plus(N m, Var n),N e1),b)->(Plus(N(e1+m),Var n),b)
	|(Plus(Plus(N m, Var n),Var l),b)->if(n=l) then (Plus( N(m+1),Var n),b) else (Plus(Plus(N m, Var n),Var l),b)
	|(Plus(Plus(Var n, N m),Var l),b)->if(n=l) then (Plus( N(m+1),Var n),b) else (Plus(Plus(N m, Var n),Var l),b)
	|(Plus(Var l,Plus(N m, Var n)),b)->if(n=l) then (Plus( N(m+1),Var n),b) else (Plus(Plus(N m, Var n),Var l),b)
	|(Plus(Var l,Plus(N m, Var n)),b)->if(n=l) then (Plus( N(m+1),Var n),b) else (Plus(Plus(N m, Var n),Var l),b)
	|(Plus(Mult(Var n, N m),Var k),b)->if(n=k) then (Mult(N(m+1),Var n),b) else (Plus(Mult(N m ,Var n),Var k),b)
	|(Plus(Var k,Mult(Var n, N m)),b)->if(n=k) then (Mult(N(m+1),Var n),b) else (Plus(Mult(N m, Var n),Var k),b)
	|(Plus(Mult(N m,Var n),Var k),b)->if(n=k) then (Mult(N(m+1),Var n),b) else (Plus(Mult(N m ,Var n),Var k),b)
	|(Plus(Var k,Mult(N m,Var n)),b)->if(n=k) then (Mult(N(m+1),Var n),b) else (Plus(Mult(N m ,Var n),Var k),b)
	|(Mult (N e1, Mult(N m,Var n)),b)->(Mult(N(e1*m),Var n),b)
	|(Mult (N e1, Mult(Var n,N m)),b)->(Mult(N(e1*m),Var n),b)
	|(Mult (Mult(N m,Var n),N e1),b)->(Mult(N(e1*m),Var n),b)
	|(Mult (Mult(Var n,N m),N e1),b)->(Mult(N(e1*m),Var n),b)
	|(Mult ( Plus(N k, N l), Plus(N m , Var n)),b)-> (Mult(N((k+l)*m),Var n),b)
	|(Mult ( Plus(N k, N l), Plus(Var n , N m)),b)-> (Mult(N((k+l)*m),Var n),b)
	|(Mult ( Plus(N k, Var l), Plus(N n , N m)),b)-> (Mult(N((n+m)*k),Var l),b)
	|(Mult ( Plus(Var l, N k), Plus(N n , N m)),b)-> (Mult(N((n+m)*k),Var l),b)
	|(Mult (N e1,N e2),b)->(N (e1 * e2),b)
	|(Plus(N e1, N e2),b)->(N(e1+e2),b)
	|(Mult (N e1, e2),b)->(cals(Mult(N e1,cal(e2)),1))
	|(Mult (e2, N e1),b)-> (cals(Mult(N e1,cal(e2)),1))
	|(Plus(e2,N e1),b)-> (cals(Plus(N e1, cal(e2)),1))
	|(Plus(N e1, e2),b)->cals(Plus((N e1), cal (e2)) , 1)
	|(Plus(e1,e2),b)-> cals(Plus(cal(e1),cal(e2)) , 1)
	|(Mult(e1,e2),b)-> cals(Mult(cal(e1),cal(e2)),1)
and cal:exp->exp = fun e->
	match e with
	N e ->N e
	|Var e -> Var e
	|Plus (Mult (N k,Var l),Mult (N m,Var n))->if ( l=n) then Mult (N(k+m),Var l) else Plus (Mult (N k,Var l),Mult (N m,Var n))
	|Plus (Mult (N k,Var l),Mult (Var n,N m))->if ( l=n) then Mult (N(k+m),Var l) else Plus (Mult (N k,Var l),Mult (N m,Var n))
	|Plus (Mult (Var l,N k),Mult (Var n,N m))->if ( l=n) then Mult (N(k+m),Var l) else Plus (Mult (N k,Var l),Mult (N m,Var n))
	|Plus (Mult (Var l,N k),Mult (N m,Var n))->if ( l=n) then Mult (N(k+m),Var l) else Plus (Mult (N k,Var l),Mult (N m,Var n))
	|Plus (N e1,N e2)->N (e1 + e2)
	|Plus(N e1, Plus(N m, Var n))->Plus(N(e1+m),Var n)
	|Plus(N e1, Plus(Var n, N m))->Plus(N(e1+m),Var n)
	|Plus( Plus(Var n, N m),N e1)->Plus(N(e1+m),Var n)
	|Plus( Plus(N m, Var n),N e1)->Plus(N(e1+m),Var n)
	|Plus(Plus(N m, Var n),Var l)->if(n=l) then Plus( N(m+1),Var n) else Plus(Plus(N m, Var n),Var l)
	|Plus(Plus(Var n, N m),Var l)->if(n=l) then Plus( N(m+1),Var n) else Plus(Plus(N m, Var n),Var l)
	|Plus(Var l,Plus(N m, Var n))->if(n=l) then Plus( N(m+1),Var n) else Plus(Plus(N m, Var n),Var l)
	|Plus(Var l,Plus(N m, Var n))->if(n=l) then Plus( N(m+1),Var n) else Plus(Plus(N m, Var n),Var l)
	|Plus(Mult(Var n, N m),Var k)->if(n=k) then Mult(N(m+1),Var n) else Plus(Mult(N m ,Var n),Var k)
	|Plus(Var k,Mult(Var n, N m))->if(n=k) then Mult(N(m+1),Var n) else Plus(Mult(N m, Var n),Var k)
	|Plus(Mult(N m,Var n),Var k)->if(n=k) then Mult(N(m+1),Var n) else Plus(Mult(N m ,Var n),Var k)
	|Plus(Var k,Mult(N m,Var n))->if(n=k) then Mult(N(m+1),Var n) else Plus(Mult(N m ,Var n),Var k)
	|Mult (N e1, Mult(N m,Var n))->Mult(N(e1*m),Var n)
	|Mult (N e1, Mult(Var n,N m))->Mult(N(e1*m),Var n)
	|Mult (Mult(N m,Var n),N e1)->Mult(N(e1*m),Var n)
	|Mult (Mult(Var n,N m),N e1)->Mult(N(e1*m),Var n)
	|Mult ( Plus(N k, N l), Plus(N m , Var n))-> Mult(N((k+l)*m),Var n)
	|Mult ( Plus(N k, N l), Plus(Var n , N m))-> Mult(N((k+l)*m),Var n)
	|Mult ( Plus(N k, Var l), Plus(N n , N m))-> Mult(N((n+m)*k),Var l)
	|Mult ( Plus(Var l, N k), Plus(N n , N m))-> Mult(N((n+m)*k),Var l)
	|Mult (N e1,N e2)->N (e1 * e2)
	|Mult (N e1, e2)->fst (cals(Mult(N e1,cal(e2)),0))
	|Mult (e2, N e1)->fst (cals(Mult(N e1,cal(e2)),0))
	|Plus(N e1, e2)->fst (cals(Plus(N e1, cal(e2)),0))
	|Plus(e2,N e1)->fst (cals(Plus(N e1, cal(e2)),0))
	|Plus(e1,e2)->fst (cals(Plus(cal(e1),cal(e2)),0))
	|Mult(e1,e2)-> fst(cals(Mult(cal(e1),cal(e2)),0));;

let rec optimize:cmd->cmd = fun c->
	match c with
	Skip->Skip
	| Seq(a,b) -> Seq(optimize(a),optimize(b))
	| Assign(a,b)-> Assign(a,cal(b))
	| If(a,b,c)->If(cal(a),optimize(b),optimize(c))
	| While(a,b)->While(cal(a),optimize(b));;

optimize(Assign("x", ( Plus( Mult(N 7, Var "x") , Plus( Mult(N 7,Var "x"), Mult(N 8, Var "x")  ) ))  ));;
optimize(Assign("x",Plus(Plus(N 3, Mult(N 5, N 7)),Plus(N 3, Plus(N 5, N 7)))));;
optimize(Assign("x", Plus(Mult(N 7,Var "x"), Var "x")  ) );;

optimize(Seq( Assign("x",Plus(Mult(Var "x",N 5), Plus(Mult(N 7,Var "x"), Var "x") ) ) , While( N 5, ( Assign( "y", Mult(Plus(N 5, Plus(N 7, Mult( N 5, N 7)) ) , Var "y") ) ) ) ));;