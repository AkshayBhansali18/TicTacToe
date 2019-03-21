package com.example.aksha.tictactoe;

public class UserInfo {
    String username;
    int rank,totalscore;
    String docid;

    public UserInfo(String username, int rank, int totalscore) {
        this.username = username;
        this.rank = rank;
        this.totalscore = totalscore;
    }
    UserInfo()
    {}

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(int totalscore) {
        this.totalscore = totalscore;
    }
}
