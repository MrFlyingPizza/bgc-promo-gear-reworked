const path = require('path');

module.exports = {
    mode: 'development',
    entry: {
        cart: './src/cart.tsx'
    },
    devtool: 'inline-source-map',
    watchOptions: {
        ignored: '**/node_modules'
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
};