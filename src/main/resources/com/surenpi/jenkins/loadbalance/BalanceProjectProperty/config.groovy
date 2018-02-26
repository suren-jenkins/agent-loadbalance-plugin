package com.surenpi.jenkins.loadbalance;

import static com.surenpi.jenkins.loadbalance.BalanceProjectProperty.DescriptorImpl.AGENT_LOAD;

def f = namespace(lib.FormTagLib);

f.optionalBlock(name: AGENT_LOAD, title: _('agent.load'), checked: instance != null) {
    f.entry(field: 'memory', title: _('agent.load.memory')) {
        f.textbox()
    }

    f.entry(field: 'disk', title: _('agent.load.disk')) {
        f.textbox()
    }
}
