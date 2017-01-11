let hd a=
	match a with
	[]->raise(Failure "hd:empty list")
	|hd::tl->hd;;

let tl a
	=match a with
	[]->raise(Failure "tl:empty list")
	|hd::tl->tl;;

let rec nth a n =
	if n=0 then hd a
	else nth (tl a)(n-1);;

type graph = (vertex * vertex) list
and vertex = int;;

let rec delete :graph*(vertex*vertex)->graph=fun(a,(b,c))->
	match (a,(b,c)) with
	([],(b,c))->[]
	|(a,(b,c))->if (hd a=(b,c)) then [] @ delete(tl a,(b,c)) else [hd a] @ delete(tl a,(b,c));;

let rec reachin:graph*vertex*graph->vertex list=fun(a,b,c)->
	match (a,b,c) with
	([],b,c)->[]
	|(a,b,c)->if(fst(hd a)=b) then [snd(hd a)] @ reachin(tl a,b,c) @ reachin(delete( c, (hd a)), snd(hd a), delete(c, (hd a))) else reachin(tl a,b,c) ;;

let reach:graph*vertex->vertex list=fun (a,b)->
	[b] @ reachin(a,b,a);;


reach ([(1,2);(2,3);(3,4);(4,2);(2,5)], 1);;
reach ([(1,2);(2,3);(3,4);(4,2);(2,5)], 2);;
reach ([(1,2);(2,3);(3,4);(4,2);(2,5)], 3);;
reach ([(1,2);(2,3);(3,4);(4,2);(2,5)], 4);;
reach ([(1,2);(2,3);(3,4);(4,2);(2,5)], 5);;
