public class StandardLookupDemoBean implements DemoBean {
    private Singer singer;

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getMySinger() {
        return singer;
    }

    @Override
    public void doSomething() {
        singer.sing();
    }
}
