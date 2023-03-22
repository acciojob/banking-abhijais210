package com.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isValidId(this.tradeLicenseId)){
            //if invalid then we try to rearrange and create a valid id
            String Id = generateValidId(this.tradeLicenseId);
            if(Id.equals("")){
                throw new Exception("Valid License can not be generated");
            }else{
                this.tradeLicenseId = Id;
            }
        }
    }
    //check if license id is valid or invalid
    public boolean isValidId(String tradeLicenseId){
        int n = tradeLicenseId.length();

        for(int i = 0; i < n-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1))
                return false;
        }
        return true;
    }
    //generate valid license Id
    public String generateValidId(String s){
        Map<Character,Integer> map = new HashMap<>();
        // order all characters present in string in desending order of their count
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b)->{
            return (map.get(b)).compareTo(map.get(a));
        });

        int len = s.length();
        char ch;

        for(int i=0;i<len;i++){
            ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            pq.add(entry.getKey());
        }

        StringBuilder sb = new StringBuilder();

        //run till all characters are used to make new string
        while(!pq.isEmpty()){
            char a = pq.poll();
            if(pq.isEmpty()){
                // if more than one characters of same type is left then reorganizing is not possible
                if(map.get(a)>1)
                    return "";
                else
                    sb.append(a);
                break;
            }
            char b = pq.poll();

            //1.use the characters a and b and descrease their count in map
            //2.if count is still more than 0 after this then we need to add back to the queue.

            sb.append(a);
            map.put(a,map.get(a)-1);
            sb.append(b);
            map.put(b,map.get(b)-1);

            if(map.get(a)!=0)
                pq.add(a);

            if(map.get(b)!=0)
                pq.add(b);
        }

        return sb.toString();
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
