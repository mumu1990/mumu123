package com.tangyan.zhufengfm.app.tasks;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-28
 */

/**
 * TackCallback 异步任务执行成功之后，由 onPostExecute 来回调
 */
public interface TaskCallback {

    /**
     * 当异步任务执行成功，进行回调
     * @param result
     */
    void onTaskFinished(TaskResult result);

}
