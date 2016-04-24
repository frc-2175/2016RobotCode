package org.usfirst.frc2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;

public class LogMessageCommand extends BaseCommand {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final String msg;

    public LogMessageCommand(String msg) {
        this.msg = msg;
    }

    @Override
    protected void execute() {
        log.info(msg);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
    }

}
