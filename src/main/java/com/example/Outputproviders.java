package com.example;

class Mystack{
    int size;
    int top;
    String val[];

    Mystack(int n){
        size=n;
        top=-1;
        val=new String[n];
    }

    boolean isfull(){
        return top==size-1;
    }

    boolean isempty(){
        return top==-1;
    }

    void push(String a){
        if(isfull()){
            System.out.println("The stack is full");
            return;
        }
        val[++top]=a;
    }

    String pop(){
        if(isempty()){
            System.out.println("The stack is empty");
            return "";
        }
        return val[top--];
    }

    String peek(){
        if(isempty()){
            System.out.println("The stack is empty");
            return "";
        }
        return val[top];
    }

    void printstack(){
        if(isempty()){
            System.out.println("The stack is empty");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.print(val[i]+"  ");
        }
        System.out.println();
    }
}


public class Outputproviders{

    String firsthigher="^";
    String secondhigher="*/";
    String thirdhigher="-+";
    String alphas="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    String oper="+-*/^";

    int getpforintopost(String instack,String incoming){
        if((firsthigher.contains(incoming))&(secondhigher.contains(instack))){
            return 0;
        }
        if((firsthigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 0;
        }
        if((secondhigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 0;
        }
        if((secondhigher.contains(incoming))&(firsthigher.contains(instack))){
            return 1;
        }
        if((thirdhigher.contains(incoming))&(firsthigher.contains(instack))){
            return 1;
        }
        if((thirdhigher.contains(incoming))&(secondhigher.contains(instack))){
            return 1;
        }
        if((firsthigher.contains(incoming))&(firsthigher.contains(instack))){
            return 0;
        }
        if((secondhigher.contains(incoming))&(secondhigher.contains(instack))){
            return 1;
        }
        if((thirdhigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 1;
        }
        return -1;
    }

    int getpforintopre(String instack,String incoming){
        if((firsthigher.contains(incoming))&(secondhigher.contains(instack))){
            return 0;
        }
        if((firsthigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 0;
        }
        if((secondhigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 0;
        }
        if((secondhigher.contains(incoming))&(firsthigher.contains(instack))){
            return 1;
        }
        if((thirdhigher.contains(incoming))&(firsthigher.contains(instack))){
            return 1;
        }
        if((thirdhigher.contains(incoming))&(secondhigher.contains(instack))){
            return 1;
        }
        if((firsthigher.contains(incoming))&(firsthigher.contains(instack))){
            return 1;
        }
        if((secondhigher.contains(incoming))&(secondhigher.contains(instack))){
            return 0;
        }
        if((thirdhigher.contains(incoming))&(thirdhigher.contains(instack))){
            return 0;
        }
        return -1;
    }

    public String infixToPostfix(String input){
        String result="";
        Mystack s=new Mystack(input.length());
        for (int i=0;i<input.length();i++){
            if(!alphas.contains(Character.toString(input.charAt(i)))) {
                if((s.isempty())|(s.peek().equals("("))){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(Character.toString(input.charAt(i)).equals("(")){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(Character.toString(input.charAt(i)).equals(")")){
                    while(!(s.peek().equals("("))){
                        result+=s.pop();
                    }
                    s.pop();
                }
                else if(getpforintopost(s.peek(),Character.toString(input.charAt(i)))==0){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(getpforintopost(s.peek(),Character.toString(input.charAt(i)))==1){
                    while(!((getpforintopost(s.peek(),Character.toString(input.charAt(i))))==0)){
                        if(s.isempty()) break;
                        if(s.peek().equals("(")) break;
                        result+=s.pop();
                    }
                    s.push(Character.toString(input.charAt(i)));
                }
            }
            else{
              result+=input.charAt(i);  
            }
            if(i==input.length()-1){
                while(!(s.isempty())){
                    result+=s.pop();
                }
            }
        }
        return result;
    }

    public String infixToPrefix(String input){
        String input1="";
        for(int i=0;i<input.length();i++){
            input1=input.charAt(i)+input1;
        }
        input=input1;
        String result="";
        Mystack s=new Mystack(input.length());
        for (int i=0;i<input.length();i++){
            if(!alphas.contains(Character.toString(input.charAt(i)))) {
                if((s.isempty())|(s.peek().equals(")"))){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(Character.toString(input.charAt(i)).equals(")")){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(Character.toString(input.charAt(i)).equals("(")){
                    while(!(s.peek().equals(")"))){
                        result+=s.pop();
                    }
                    s.pop();
                }
                else if(getpforintopre(s.peek(),Character.toString(input.charAt(i)))==0){
                    s.push(Character.toString(input.charAt(i)));
                }
                else if(getpforintopre(s.peek(),Character.toString(input.charAt(i)))==1){
                    while(!((getpforintopre(s.peek(),Character.toString(input.charAt(i))))==0)){
                        if(s.isempty()) break;
                        if(s.peek().equals(")")) break;
                        result+=s.pop();
                    }
                    s.push(Character.toString(input.charAt(i)));
                }
            }
            else{
              result+=input.charAt(i);  
            }
            if(i==input.length()-1){
                while(!(s.isempty())){
                    result+=s.pop();
                }
            }
        }
        String input2="";
        for(int i=0;i<result.length();i++){
            input2=result.charAt(i)+input2;
        }
        result=input2;
        return result;
    }

    String postfixToInfix(String input){
        String result="";
        Mystack s=new Mystack(input.length());
        for(int i=0;i<input.length();i++){
            if(alphas.contains(Character.toString(input.charAt(i)))){
                s.push(Character.toString(input.charAt(i)));
            }
            else if(oper.contains(Character.toString(input.charAt(i)))){
                String op1=s.pop();
                String op2=s.pop();
                String finalexp="("+op2+Character.toString(input.charAt(i))+op1+")";
                s.push(finalexp);                
            }
            if(i==input.length()-1){
                result=s.pop();
            }
        }
        return result;
    }

    public String prefixToInfix(String input){
        String result="";
        Mystack s=new Mystack(input.length());
        for(int i=input.length()-1;i>=0;i--){
            if(alphas.contains(Character.toString(input.charAt(i)))){
                s.push(Character.toString(input.charAt(i)));
            }
            else if(oper.contains(Character.toString(input.charAt(i)))){
                String op1=s.pop();
                String op2=s.pop();
                String finalexp="("+op1+Character.toString(input.charAt(i))+op2+")";
                s.push(finalexp);                
            }
            if(i==0){
                result=s.pop();
            }
        }
        return result;
    }

    public String prefixToPostfix(String input){
        String in=prefixToInfix(input);
        return infixToPostfix(in);
    }

    public String postfixToPrefix(String input){
        String in=postfixToInfix(input);
        return infixToPrefix(in);
    }

    public static void main(String[] args) {
        Outputproviders owner=new Outputproviders();
        System.out.println(owner.infixToPrefix("A+(B*C)-(D/E)"));
    }

}