module.exports = {
    root: true,
    env: {
        node: true,
    },
    plugins: [
        'vue',
    ],
    extends: [
        'plugin:vue/essential',
        'plugin:vue/strongly-recommended',
        'plugin:vue/recommended',
        'eslint:recommended',
    ],
    parserOptions: {
        parser: 'babel-eslint',
        sourceType: 'module',
    },
    rules: {
        // "off" or 0 - turn the rule off
        // "warn" or 1 - turn the rule on as a warning (doesn’t affect exit code)
        // "error" or 2 - turn the rule on as an error (exit code is 1 when triggered)
        // "no-var": 0,
        'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
        indent: [
            'warn',
            4,
            { 
                ignoredNodes: ["TemplateLiteral"] 
            }
        ],
        'vue/html-indent': [
            'warn',
            4,
            {
                attribute: 1,
                closeBracket: 0,
                alignAttributesVertically: true,
                ignores: [],
            },
        ],
        'vue/html-closing-bracket-newline': [
            'warn',
            {
                singleline: 'never',
                multiline: 'always',
            },
        ],
        semi: [
            'warn',
            'always',
        ],
        quotes: [
            'warn',
            'single',
            {
                allowTemplateLiterals: true,
            },
        ],
        'no-empty': [
            'error',
            {
                allowEmptyCatch: true,
            },
        ],
    },
    settings: {
        'import/resolver': {
            alias: {
                map: [
                    ['@', './resources'],
                    ['~', './resources/js'],
                ],
                extensions: ['.js', '.vue'],
            },
        },
    },
};
