package com.technoidtintin.android.expandablerecyclerp1;

public class listItem {

    private String id, title, overView, release, posterPath, backdropPath;
    private boolean expanded;

    public listItem(String id, String title, String overView, String release, String posterPath, String backdropPath) {
        this.id = id;
        this.title = title;
        this.overView = overView;
        this.release = release;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }

    public String getRelease() {
        return release;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
