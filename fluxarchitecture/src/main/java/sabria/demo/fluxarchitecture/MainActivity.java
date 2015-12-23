package sabria.demo.fluxarchitecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import sabria.demo.fluxarchitecture.actions.ActionsCreator;
import sabria.demo.fluxarchitecture.dispatcher.Dispatcher;
import sabria.demo.fluxarchitecture.stores.TodoStore;
import sabria.demo.fluxarchitecture.view.TodoRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText mainInput;
    private ViewGroup mainLayout;
    private CheckBox mainCheck;

    Dispatcher dispatcher;
    ActionsCreator actionsCreator;
    TodoStore todoStore;
    private TodoRecyclerAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
        initView();
        initAdapter();
    }

    private void initAdapter() {
        RecyclerView mainList = (RecyclerView) findViewById(R.id.main_list);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new TodoRecyclerAdapter(actionsCreator);
        mainList.setAdapter(listAdapter);
    }


    private void initDependencies() {
        dispatcher = Dispatcher.getInstance(new Bus());
        actionsCreator = ActionsCreator.getInstance(dispatcher);
        todoStore = TodoStore.getInstance(dispatcher);
    }

    private void initView() {
        mainLayout = ((ViewGroup) findViewById(R.id.main_layout));
        mainInput = (EditText) findViewById(R.id.main_input);
        Button mainAdd = (Button) findViewById(R.id.main_add);
        mainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodo();
            }
        });
    }

    private void addTodo() {
        if (validateInput()) {
            actionsCreator.create(getInputText());
        }
    }

    private boolean validateInput() {
        return !TextUtils.isEmpty(getInputText());
    }

    private String getInputText() {
        return mainInput.getText().toString();
    }

    //接收消息
    @Subscribe
    public void onTodoStoreChange(TodoStore.TodoStoreChangeEvent event) {
        updateUI();
    }

    private void updateUI() {
        Toast.makeText(MainActivity.this,"--updateUI--",Toast.LENGTH_LONG).show();
        listAdapter.setItems(todoStore.getTodos());
    }


    @Override
    protected void onResume() {
        super.onResume();
        /**
         * otto最奇怪的地方就是必须所有的使用otto类都必须register
         * otto只支持在一个类发消息通信比如(Activity和Fragemnt)，不支持两个Activity之间的通信
         */
        dispatcher.register(this);
        dispatcher.register(todoStore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         * otto最奇怪的地方就是必须所有的使用otto类都必须register
         */
        dispatcher.unregister(this);
        dispatcher.unregister(todoStore);
    }
}
