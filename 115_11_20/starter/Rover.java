// spendEnergy：嘗試扣除 amount 格能源。amount 必須是正數且不超過目前能源，
    // 成功就扣除並回傳 true；否則回傳 false，能源不變。
    // TODO 1：支援擴充及局部重構。先完成扣能源，跟做 explore，再獨立改 scan。
    // 這個方法只守住扣能源規則，不負責停止、加樣本或計次。
    protected boolean spendEnergy(int amount) {
        if (amount <= 0 || amount > energy) { return false; }
        energy -= amount;
        return true;
    }

    // addSamples：增加 amount 份樣本。只接受正數，0 或負數會被忽略。
    // 已提供；停止檢查與「整組行動」仍由呼叫它的行動方法（例如 explore）負責。
    protected void addSamples(int amount) { if (amount > 0) { samples += amount; } }

    // countAction：成功行動次數加 1。由行動方法在行動成功後呼叫。
    protected void countAction() { actionCount++; }

    // explore：探索。用 spendEnergy 扣 6 格，成功才加 2 份樣本並計一次行動；能源不足回「能源不足」。
    private String explore() {
        if (!spendEnergy(6)) { return "能源不足"; }
        addSamples(2);
        countAction();
        return "成功";
    }

    // scan：掃描。用 spendEnergy 扣 2 格，成功才加 1 份樣本並計一次行動；能源不足回「能源不足」。
    private String scan() {
        if (!spendEnergy(2)) { return "能源不足"; }
        addSamples(1);
        countAction();
        return "成功";
    }