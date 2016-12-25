package comt.example.administrator.recycerviewdemo01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 1.显示基本的RecyclerView
 * TODO 2.讲解分割线  水平和垂直
 * TODO 3.ListView的item 最外层自动变为自适应
 *         RecyclerView item的最外层match_parent最多展示2个
 * TODO 4.添加分割线
 *         RecyclerVeiw 和 item添加背景   item写margin属性
 *         通过设置 addItemDecoration 去实现分割线
 *         创建ItemDecoration
              @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);//获取item在Adapter中是第几个
            //设置偏移量
            outRect.set(0,0,0,position*5);
   TODO 5。点击和滚动事件

   TODO 6. 移除数据

   TODO 7. 添加数据
 *
 *
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.OnChlidClickListener {

    private RecyclerView recyclerView;
    private List<String> data;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);

        data = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            data.add("item"+i);
        }

        adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);


        //设置分割线
        RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {
            /**
             * 在子控件绘制之前绘制的
             * 如果子控件添加backgroup 那么会覆盖
             * @param c
             * @param parent
             * @param state
             */
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            /**
             *在子控件绘制之后绘制的
             *
             * @param c
             * @param parent
             * @param state
             */
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                c.drawCircle(200,200,50,paint);

            }

            /**
             *
             * @param outRect item的矩形范围
             * @param view  单条视图
             * @param parent Recycler
             * @param state
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);//获取item在Adapter中是第几个
                //设置偏移量
//                outRect.set(0,0,0,position*5);
            }
        };


        recyclerView.addItemDecoration(decoration);

        //删除 移除 --》 移动
        //添加 移动 --》 添加
        //设置动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        recyclerView.setItemAnimator(new MyAnimator());

        adapter.setChlidClickListener(this);

    }


    @Override
    public void onChlidClick(int position, long id, String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
//        adapter.remove(position);
         adapter.add(position,data+"复制品");
    }
}
