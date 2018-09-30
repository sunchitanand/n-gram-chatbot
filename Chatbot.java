import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }

    static public void main(String[] args){

        ArrayList<Integer> corpus = readCorpus();
        int ctr;
        int cnt[] = new int[4700];

        int flag = Integer.valueOf(args[0]);
        if(flag == 100){
            int w = Integer.valueOf(args[1]);
            int count = 0;
            //TODO count occurence of w

            for(int i=0;i<corpus.size();i++)
            {
                if(corpus.get(i) == w) count++;
            }

            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double prob, l = 0, r = 0;
            boolean flag1=true;
            double x = (double)n1/(double)n2;
            //TODO generate

            for(int i=0;i<corpus.size();i++)
            {
                cnt[corpus.get(i)]++;
            }
            for(int i=0;i<4700;i++)
            {

                prob = cnt[i]/(double)corpus.size();
                if(prob!=0)
                {
                    l = r;
                    r = r + prob;

                    if(flag1)
                    {
                        if(x>=l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            return;
                        }
                    }
                    else 
                    {
                        if(x>l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            return;
                        }
                    }
                    flag1=false;
                }
            }

        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;

            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO

            for(int i=0;i<corpus.size()-1;i++)
            {
                if(corpus.get(i)==h)words_after_h.add(corpus.get(i+1));
                if(corpus.get(i)==h && corpus.get(i+1)==w) count++;
            }

            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            double prob, l=0, r=0;
            boolean flag1=true;
            double x = (double)n1/(double)n2;

            for(int i=0;i<corpus.size()-1;i++)
            {
                if(corpus.get(i)==h)
                    cnt[corpus.get(i+1)]++;
            }

            for(int i=0;i<corpus.size()-1;i++)
            {
                if(corpus.get(i)==h)words_after_h.add(corpus.get(i+1));
            }

            for(int i=0;i<4700;i++)
            {
                prob = cnt[i]/(double)words_after_h.size();
                if(prob!=0)
                {
                    l = r;
                    r = r + prob;

                    if(flag1)
                    {
                        if(x>=l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            return;
                        }
                    }

                    else 
                    {
                        if(i!=0 && x>l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            return;
                        }
                    }
                    flag1=false;
                }
            }

        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO

            for(int i=0;i<corpus.size()-2;i++)
            {
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2 && corpus.get(i+2)==w) count++;
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2) words_after_h1h2.add(corpus.get(i+2));
            }
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600)
        {
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();

            double prob, l=0, r=0;
            double x = (double)n1/(double)n2;
            boolean flag1=true;

            for(int i=0;i<4700;i++)
                cnt[i]=0;

            for(int i=0;i<corpus.size()-2;i++)
            {
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2)
                {
                    cnt[corpus.get(i+2)]++;
                    words_after_h1h2.add(corpus.get(i+2));
                }
            }

            if(words_after_h1h2.size()==0)
            {
                System.out.println("undefined");
                return;
            }

            for(int i=0;i<4700;i++)
            {

                prob = cnt[i]/(double)words_after_h1h2.size();
                if(prob!=0)
                {
                    l = r;
                    r = r + prob;
                    if(flag1)
                    {
                        if(x>=l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            break;
                        }
                    }
                    else 
                    {
                        if(i!=0 && x>l && x<=r)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",l)+"\n"+String.format("%.7f",r)+"\n");
                            break;
                        }
                    }
                    flag1=false;
                }
            }

        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;
            int count;
            double prob, l=0,r=0;
            boolean flag1=true;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double x = rng.nextDouble();

                for(int i=0;i<corpus.size();i++)
                {
                    cnt[corpus.get(i)]++;
                }

                for(int i=0;i<4700;i++)
                {
                    prob = cnt[i]/(double)corpus.size();
                    if(prob!=0)
                    {
                        l = r;
                        r = r + prob;

                        if(flag1)
                        {
                            if(x>=l && x<=r)
                            {
                                h1=i;
                                break;
                            }
                        }

                        else 
                        {
                            if(i!=0 && x>l && x<=r)
                            {
                                h1=i;
                                break;
                            }
                        }
                        flag1=false;
                    }
                }
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                x = rng.nextDouble();
                l=0;r=0;
                flag1=true;
                //TODO
                for(int i=0;i<4700;i++)
                    cnt[i]=0;

                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h1)words_after_h.add(corpus.get(i+1));
                }

                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h1)
                        cnt[corpus.get(i+1)]++;
                }

                for(int i=0;i<4700;i++)
                {
                    prob = cnt[i]/(double)words_after_h.size();
                    if(prob!=0)
                    {
                        l = r;
                        r = r + prob;

                        if(flag1)
                        {
                            if(x>=l && x<=r)
                            {
                                h2=i;
                                break;
                            }
                        }
                        else 
                        {
                            if(i!=0 && x>l && x<=r)
                            {
                                h2=i;
                                break;
                            }
                        }
                        flag1=false;
                    }
                }
                System.out.println(h2);
            }

            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double x = rng.nextDouble();

                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h1)words_after_h.add(corpus.get(i+1));
                }

                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h1)
                        cnt[corpus.get(i+1)]++;
                }

                for(int i=0;i<4700;i++)
                {
                    prob = cnt[i]/(double)words_after_h.size();
                    if(prob!=0)
                    {
                        l = r;
                        r = r + prob;

                        if(flag1)
                        {
                            if(x>=l && x<=r)
                            {
                                h2=i;
                                break;
                            }
                        }
                        else 
                        {
                            if(x>l && x<=r)
                            {
                                h2=i;
                                break;
                            }
                        }
                        flag1=false;
                    }
                }
                System.out.println(h2);
            }

            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double x = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                l=0.0;r=0.0;
                flag1=true;
                words_after_h1h2.clear();
                for(int i=0;i<4700;i++)
                    cnt[i]=0;
                for(int i=0;i<corpus.size()-2;i++)
                {
                    if(corpus.get(i)==h1 && corpus.get(i+1)==h2) words_after_h1h2.add(corpus.get(i+2));
                }

                if(words_after_h1h2.size()!=0)
                {
                    for(int i=0;i<corpus.size()-2;i++)
                    {
                        if(corpus.get(i)==h1 && corpus.get(i+1)==h2)
                            cnt[corpus.get(i+2)]++;
                    }
                    for(int i=0;i<4700;i++)
                    {
                        prob = cnt[i]/(double)words_after_h1h2.size();
                        if(prob!=0)
                        {
                            l = r;
                            r = r + prob;
                            if(flag1)
                            {
                                if(x>=l && x<=r)
                                {
                                    w=i;
                                }
                            }
                            else 
                            {
                                if(x>l && x<=r)
                                {
                                    w=i;
                                }
                            }
                        }
                        flag1=false;
                    }
                }
                else
                {
                    //sas
                    for(int i=0;i<corpus.size()-1;i++)
                    {
                        if(corpus.get(i)==h2)words_after_h.add(corpus.get(i+1));
                    }

                    for(int i=0;i<corpus.size()-1;i++)
                    {
                        if(corpus.get(i)==h2)
                            cnt[corpus.get(i+1)]++;
                    }

                    for(int i=0;i<4700;i++)
                    {

                        prob = cnt[i]/(double)words_after_h.size();
                        if(prob!=0)
                        {
                            l = r;
                            r = r + prob;

                            if(flag1)
                            {
                                if(x>=l && x<=r)
                                {
                                    w=i;
                                    break;
                                }
                            }

                            else 
                            {
                                if(x>l && x<=r)
                                {
                                    w=i;
                                    break;
                                }
                            }
                            flag1=false;
                        }
                    }
                    //sasa
                }

                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }
        return;
    }
}
