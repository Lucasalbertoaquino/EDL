import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;
import java.io.*;

import figuras.*;

class IfaceApp{
    public static void main(String[] args){ // MEMORIA ESTATICA 
        IfaceFrame frame = new IfaceFrame(); // PILHA ( semelhante ao uso do malloc)
        frame.setVisible(true);

    }
}
class IfaceFrame extends JFrame{
    ArrayList<Figure> figs = new ArrayList<Figure>(); //PILHA 
    Figure focus = null; //PILHA
    Random rand = new Random(); 
    Scanner teclado = new Scanner(System.in);
    String s;//PILHA

    int xm,ym; //PILHA

    int xt,yt;  //PILHA

    Button focus_but = null; //PILHA

    ArrayList<Button> buts = new ArrayList<Button>(); // PILHA

    int fixai = -1;
    boolean mutex; 


    IfaceFrame(){
        try{
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList <Figure>) o.readObject();
            o.close();
        }catch (Exception x){
            System.out.println("Erro!");
        }


        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    try{
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    }catch (Exception x){}
                    System.exit(0);
                }
            }
        );

        buts.add(new Button(0,new Estrela(0,0,0,0,0,0,0,0,0,0))); //HEAP
        buts.add(new Button(1,new Ellipse(40,60,30,30,0,0,0,0,0,0))); // HEAP
        buts.add(new Button(2,new Triangulo(40,60,30,30,0,0,0,0,0,0))); //HEAP
        buts.add(new Button(3,new Rect(40,60,30,30,0,0,0,0,0,0))); //HEAP
        buts.add(new Button(4,new Lineb(40,60,30,30,255,0,0,0,0,0)));//HEAP
        buts.add(new Button(5,new Triangulo(40,60,30,30,255,255,0,0,0,0)));// HEAP
        


        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    if((evt.getKeyChar() == 'r'||evt.getKeyChar() == 'R')){
                        // insere as cores aleatoriamente
                        int rf = rand.nextInt(255);//define a cor de 256 bits caso nao seja definida pelo user
                        int gf = rand.nextInt(255);
                        int bf = rand.nextInt(255);
                        int x = xm; 
                        int y = ym; 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        Rect r = new Rect(x,y,w,h,rf,gf,bf,rl,gl,bl); //HEAP
                        figs.add(r); //PILHA
                    }
                    if((evt.getKeyChar() == 'f')||(evt.getKeyChar() =='F')){
                        //insere as cores manualmente
                        System.out.printf("*****Inserir cores do Retangulo manualmente*****\n");
                        System.out.printf("R: ");
                        int rf = teclado.nextInt();
                        System.out.printf("G: ");
                        int gf = teclado.nextInt();
                        System.out.printf("B: ");
                        int bf = teclado.nextInt();
                        if((rf < 0 || rf > 255)||(gf < 0|| gf > 255)||(bf < 0 ||bf > 255)){
                            System.out.println("***Voce inseriu um canal de cores invalido***");
                            System.out.println("Por definicao sera inserida uma figura padrao!!!\n");
                            rf = 0;
                            gf = 0;
                            bf = 0;
                        }
                        int x = xm; //recebe a coordenada x do mouse
                        int y = ym; // recebe a coordenada y do mouse
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        Rect r = new Rect(x,y,w,h,rf,gf,bf,rl,gl,bl); //HEAP
                        figs.add(r); //PILHA
                        teclado.nextLine();
                    }
                                                    
                    if((evt.getKeyChar() == 'e')||(evt.getKeyChar() == 'E')){
                        int x = xm; //recebe a coordenada x do mouse
                        int y = ym; // recebe a coordenada y do mouse
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rf = rand.nextInt(255);//define a cor de 256 bits
                        int gf = rand.nextInt(255);
                        int bf = rand.nextInt(255);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        figs.add(new Ellipse(x,y,w,h,rf,gf,bf,rl,gl,bl)); 
                    }
                    if((evt.getKeyChar() == 'd')||(evt.getKeyChar() == 'D')){
                        System.out.printf("*****Inserir cores da Elipse manualmente*****\n");
                        int x = xm; 
                        int y = ym; 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        System.out.printf("R: ");
                        int rf = teclado.nextInt();
                        System.out.printf("G: ");
                        int gf = teclado.nextInt();
                        System.out.printf("B: ");
                        int bf = teclado.nextInt();
                        if((rf < 0 || rf > 255)||(gf < 0|| gf > 255)||(bf < 0 ||bf > 255)){
                            System.out.println("***Voce inseriu um canal de cores invalido***");
                            System.out.println("Por definicao sera inserida uma figura padrao!!!\n");
                            rf = 0;
                            gf = 0;
                            bf = 0;
                        }
                        figs.add(new Ellipse(x,y,w,h,rf,gf,bf,rl,gl,bl));
                        teclado.nextLine();//limpa o buffer   
                    }
                    if((evt.getKeyChar() == 's')|| (evt.getKeyChar() =='S')){
                        int x = xm; 
                        int y = ym; 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rf = rand.nextInt(255);//define a cor de 256 bits
                        int gf = rand.nextInt(255);
                        int bf = rand.nextInt(255);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        figs.add(new Estrela(x,y,w,h,rf,gf,bf,rl,gl,bl));
                    }
                    
                    if((evt.getKeyChar() == 'w')|| (evt.getKeyChar() =='W')){
                        System.out.printf("*****Inserir cores da Estrela manualmente*****\n");
                        int x = xm;
                        int y = ym; 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        System.out.printf("R: ");
                        int rf = teclado.nextInt();
                        System.out.printf("G: "); 
                        int gf = teclado.nextInt();
                        System.out.printf("B: ");
                        int bf = teclado.nextInt();
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        if((rf < 0 || rf > 255)||(gf < 0|| gf > 255)||(bf < 0 ||bf > 255)){
                            System.out.println("***Voce inseriu um canal de cores invalido***");
                            System.out.println("Por definicao sera inserida uma figura padrao!!!\n");
                            rf = 0;
                            gf = 0;
                            bf = 0;
                        }
                        figs.add(new Estrela(x,y,w,h,rf,gf,bf,rl,gl,bl));
                        teclado.nextLine();
                    }
                    if((evt.getKeyChar() == 't')|| (evt.getKeyChar() == 'T')){
                        int x = xm; //recebe a coordenada x do mouse
                        int y = ym; // recebe a coordenada y do mouse
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rf = rand.nextInt(255);
                        int gf = rand.nextInt(255);
                        int bf = rand.nextInt(255);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        figs.add(new Triangulo(x,y,w,h,rf,gf,bf,rl,gl,bl));
                    }
                    if((evt.getKeyChar() == 'g')|| (evt.getKeyChar() == 'G')){
                        System.out.printf("*****Inserir cores do Triangulo manualmente*****\n");
                        int x = xm;
                        int y = ym; 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rl = 0;
                        int gl = 0;
                        int bl = 0;
                        System.out.printf("R: ");
                        int rf = teclado.nextInt();
                        System.out.printf("G: "); 
                        int gf = teclado.nextInt();
                        System.out.printf("B: ");
                        int bf = teclado.nextInt();
                        if((rf < 0 || rf > 255)||(gf < 0|| gf > 255)||(bf < 0 ||bf > 255)){
                            System.out.println("***Voce inseriu um canal de cores invalido***");
                            System.out.println("Por definicao sera inserida uma figura padrao!!!\n");
                            rf = 0;
                            gf = 0;
                            bf = 0;
                        }
                        figs.add(new Triangulo(x,y,w,h,rf,gf,bf,rl,gl,bl));
                        teclado.nextLine(); 
                    }
                    if((evt.getKeyChar() == 127)&&(focus != null)){
                        figs.remove(figs.get(figs.indexOf(focus))); // remove o elemento
                        if((evt.getKeyChar() == 127)&&(focus!=null)){
                            System.out.println("Elemento ja removido");
                            focus = null;
                        }
                    }
                    else if((evt.getKeyChar() == 127)&&(focus == null)){
                        System.out.println("Nada a remover");
                    }
                    repaint();
                }
            }
        );


        this.addMouseListener(
            new MouseAdapter(){
                public void mousePressed(MouseEvent evt){
                    int mx = evt.getX();
                    int my = evt.getY();
                    for(Figure fig: figs){
                        if(fig.clicked(mx,my)){
                            focus = fig;
                            fig.foco(255,0,0); 
                        }
                        else{
                            fig.foco(0,0,0);
                        }
                    }

                }
                public void mouseClicked(MouseEvent evt){
                    int mx = evt.getX();
                    int my = evt.getY();
                    int b = evt.getButton();
                    
                    for(Figure fig: figs){
                        if(fig.clicked(mx,my)){
                            fig.foco(255,0,0); 
                            if(b == 3){
                                System.out.println("***Troca de cores***");
                                System.out.printf("R: ");
                                int rf = teclado.nextInt();
                                System.out.printf("G: "); 
                                int gf = teclado.nextInt();
                                System.out.printf("B: ");
                                int bf = teclado.nextInt();
                                if((rf < 0 || rf > 255)||(gf < 0|| gf > 255)||(bf < 0 ||bf > 255)){
                                    System.out.println("***Voce inseriu um canal de cores invalido***");
                                    System.out.println("Por definicao sera inserida uma figura padrao!!!\n");
                                    rf = 0;
                                    gf = 0;
                                    bf = 0;
                                }
                                teclado.nextLine(); //limpa o buffer
                                fig.cor(rf,gf,bf);
                            }
                            
                        }
                        else{
                            fig.foco(0,0,0);
                        }
                        
                    }
                    int h = rand.nextInt(255);
                    int w = rand.nextInt(255);
                    int rf = rand.nextInt(255);
                    int gf = rand.nextInt(255);
                    int bf = rand.nextInt(255);

                    for(Button but: buts){
                        if(but.clicked(mx,my)){
                            focus_but = but;
                            fixai = but.idx;
                        }
                    }
                    if((fixai == 0)&&(focus_but.clicked(mx,my)==false)){
                        figs.add(new Estrela(mx,my,w,h,rf,gf,bf,0,0,0));
                    }
                    else if((fixai == 1)&&(focus_but.clicked(mx,my)==false)){
                        figs.add(new Ellipse(mx,my,w,h,rf,gf,bf,0,0,0));
                    }
                    else if((fixai == 2)&&(focus_but.clicked(mx,my)==false)){
                        figs.add(new Triangulo(mx,my,w,h,rf,gf,bf,0,0,0));    
                    }
                    else if((fixai == 3)&&(focus_but.clicked(mx,my)==false)){
                        figs.add(new Rect(mx,my,w,h,rf,gf,bf,0,0,0)); 
                    }
                    if(focus!= null){
                        if((fixai == 4)&&(focus_but.clicked(mx,my)==false)){
                        figs.remove(figs.get(figs.indexOf(focus)));
                        }
                    }
                    if(focus_but == null){
                        if((fixai == 5)&&(focus_but.clicked(mx,my)==false)){
                            focus_but = null;
                        }
                    }
                    repaint();
                }
            }

        );

        this.addMouseMotionListener(
            new MouseMotionAdapter(){
                public void mouseDragged(MouseEvent evt){
                    int dx = evt.getX();
                    int dy = evt.getY();    
                    if(focus != null){
                        focus.drag(dx-xm,dy-ym);
                        xm = dx;
                        ym = dy;
                    }
                    repaint();
                    
                }
                public void mouseMoved(MouseEvent evt){
                    focus = null;
                    xm = evt.getX();
                    ym = evt.getY();
                }
            }

        );

        this.setTitle("the Project part 2");
        this.setSize(350,350);
    }

   public void paint (Graphics g){
        super.paint(g);
        for(Figure fig: this.figs){
            fig.paint(g,true);
        }
        for(Button but: this.buts){
            but.paint(g,but == focus_but);
        }
    }