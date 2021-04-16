package com.zc58s.springbootinfdemo.jna.service.business.infPtz.factory;

import com.zc58s.springbootinfdemo.bean.Command;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.InfPtzCommand;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.impl.CommandBase;
import com.zc58s.springbootinfdemo.jna.utils.SpringContextUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 英飞拓云控制台操作处理工厂
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 11:22
 */
public class InfPtzFactory {

    //存储API定义
    private static Map<Integer, String> containers = new HashMap<>();
    //存储已实例化的对象实例
    private static Map<Integer, InfPtzCommand> commands = new HashMap<>();

    static {
        //目前不知道英飞拓的PtzZoomOut、PtzZooMin与海康视频的那个功能对应，暂时设置为11、12
        containers.put(11, "PtzZoomOut"); //播放摄像机的ID或伪编码
        containers.put(12, "PtzZooMin"); //控制摄像机镜头变小缩放

        containers.put(21, "PtzUp");
        containers.put(22, "PtzDown");
        containers.put(23, "PtzLeft");
        containers.put(24, "PtzRight");
        containers.put(25, "PtzUpLeft");
        containers.put(26, "PtzUpRight");
        containers.put(27, "PtzLeftDown");
        containers.put(28, "PtzRightDown");
        containers.put(8, "PtzSetPreset");
        containers.put(39, "PtzPreset");
    }

    public static InfPtzCommand GetCommand(String szCameraId, int infCommandKey, int infCommandValue) {
        /**
         *          海康定义的API约定
         *         dwPTZCommand：云台控制命令，11=焦距变大，12=焦距变小，13=焦点前调，14=焦点后调，15=光圈扩大，16=光圈扩小
         *         21=上仰，22=下俯，23=左转，24=右转，25=左上，26=右上，27=左下，28=右下，29=自动扫描
         *         8=设置预置点，9=清除预置点，39=转到预置点
         *         dwStop：云台开始或停止动作：0=开始，1=停止
         */
        InfPtzCommand command = null;
        String base = InfPtzFactory.class.getPackage().getName();
        try {
            if (containers.containsKey(infCommandKey) && !commands.containsKey(infCommandKey)) {
                Class clazz = Class.forName(base + "." + containers.get(infCommandKey));
                Constructor<?> constructors[] = clazz.getConstructors();
                Class[] parameterTypes = constructors[0].getParameterTypes();
                if (parameterTypes.length == 1) {
                    Constructor constructor = clazz.getConstructor(String.class);
                    command = (CommandBase) constructor.newInstance(szCameraId);
                }
                if (parameterTypes.length == 2) {
                    Constructor constructor = clazz.getConstructor(String.class, int.class);
                    command = (CommandBase) constructor.newInstance(szCameraId, infCommandValue);
                }
                commands.put(infCommandKey, command);
            } else if (commands.containsKey(infCommandKey)) {
                command = commands.get(infCommandKey);
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return command;
    }

}

class PtzUp extends CommandBase {
    public PtzUp(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzUp(super.szCameraId);
    }
}

class PtzDown extends CommandBase {
    public PtzDown(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzDown(super.szCameraId);
    }
}

class PtzRight extends CommandBase {

    public PtzRight(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzRight(super.szCameraId);
    }
}

class PtzLeft extends CommandBase {

    public PtzLeft(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzLeft(super.szCameraId);
    }
}

class PtzLeftDown extends CommandBase {

    public PtzLeftDown(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzLeftDown(super.szCameraId);
    }
}

class PtzUpLeft extends CommandBase {

    public PtzUpLeft(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzUpLeft(super.szCameraId);
    }
}

class PtzUpRight extends CommandBase {

    public PtzUpRight(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzUpRight(super.szCameraId);
    }
}

class PtzRightDown extends CommandBase {

    public PtzRightDown(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzRightDown(super.szCameraId);
    }
}

class PtzZooMin extends CommandBase {

    public PtzZooMin(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzZooMin(super.szCameraId);
    }
}

class PtzZoomOut extends CommandBase {

    public PtzZoomOut(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzZoomOut(super.szCameraId);
    }
}

class PtzStop extends CommandBase {

    public PtzStop(String szCameraId) {
        super(szCameraId);
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzStop(super.szCameraId);
    }
}

class PtzPreset extends CommandBase {
    private int infCommandValue;

    public PtzPreset(String szCameraId, int infCommandValue) {
        super(szCameraId);
        this.infCommandValue = infCommandValue;
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzPreset(super.szCameraId, this.infCommandValue);
    }
}

class PtzSetPreset extends CommandBase {

    private int infCommandValue;

    public PtzSetPreset(String szCameraId, int infCommandValue) {
        super(szCameraId);
        this.infCommandValue = infCommandValue;
    }

    @Override
    public PtzResponse Ptz() {
        IPtzControlService PtzService = SpringContextUtil.getBean(IPtzControlService.class);
        return PtzService.PtzSetPreset(super.szCameraId, this.infCommandValue);
    }
}


